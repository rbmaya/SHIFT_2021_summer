package ru.cft.shift2021summer.presentation.list

import android.os.Bundle
import android.view.*
import android.widget.ExpandableListView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.RangeSlider
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.cft.shift2021summer.R
import ru.cft.shift2021summer.databinding.FragmentCosmeticListBinding
import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.presentation.MainActivity
import ru.cft.shift2021summer.presentation.favorites.FavoritesCosmeticViewModel
import ru.cft.shift2021summer.utils.CosmeticsUiState
import ru.cft.shift2021summer.utils.LimitPriceEvent


@AndroidEntryPoint
class CosmeticsListFragment : Fragment() {
    private lateinit var binding: FragmentCosmeticListBinding
    private lateinit var searchView: SearchView
    private lateinit var cosmeticsRecyclerView: RecyclerView

    private val adapter = CosmeticsListAdapter {
        cosmeticsListViewModel.openDetailCosmetic(it)
    }

    private val cosmeticsListViewModel: CosmeticsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cosmetic_list, container, false)
        binding = FragmentCosmeticListBinding.bind(view)
        cosmeticsRecyclerView = binding.cosmeticsList
        cosmeticsRecyclerView.adapter = adapter
        with(activity as MainActivity) {
            setToolbar()
            setSearchOptionsListeners(createOptionsSearchListener())
            setListenerOnRemoveSearchOptions {
                cosmeticsListViewModel.loadCosmetics(false)
                closeDrawers()
            }
            setListenerOnPriceSlider(createPriceSliderListener())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cosmeticsListViewModel.loadCosmetics(false)
        cosmeticsListViewModel.loadPriceLimits()
        binding.swipeRefreshLayout.setOnRefreshListener {
            clearSearchView()
            cosmeticsListViewModel.loadCosmetics(true)
        }
        lifecycleScope.launch {
            cosmeticsListViewModel.uiState.flowWithLifecycle(lifecycle)
                .collect {
                    processListLoading(it)
                }
        }
        cosmeticsListViewModel.openDetailsEvent.observe(viewLifecycleOwner, {
            navigateToDetailCosmetic(it)
        })
        cosmeticsListViewModel.limitPriceEvent.observe(viewLifecycleOwner, {
            processPriceLimits(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                cosmeticsListViewModel.queryTextChanged(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })
    }

    private fun processPriceLimits(limits: LimitPriceEvent) {
        when (limits) {
            is LimitPriceEvent.PriceLimits -> {
                with(activity as MainActivity) {
                    setValuesOnPriceSlider(
                        valueFrom = limits.valueFrom,
                        valueTo = limits.valueTo
                    )
                }
            }
        }
    }

    private fun createPriceSliderListener(): RangeSlider.OnSliderTouchListener{
        return object : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {}

            override fun onStopTrackingTouch(slider: RangeSlider) {
                val values = slider.values
                val valueFrom = values[0]
                val valueTo = values[1]
                cosmeticsListViewModel.loadCosmeticsByPrice(valueFrom = valueFrom, valueTo = valueTo)
            }
        }
    }

    private fun createOptionsSearchListener(): ExpandableListView.OnChildClickListener {
        return ExpandableListView.OnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val group = (activity as MainActivity).getSelectedSearchGroupName(groupPosition)
            val name =
                (activity as MainActivity).getSelectedSearchOptions(groupPosition, childPosition)
            when (group.lowercase()) {
                "brand" -> cosmeticsListViewModel.loadCosmeticsByBrand(name)
                "product type" -> cosmeticsListViewModel.loadCosmeticsByProductType(name)
            }
            (activity as MainActivity).closeDrawers()
            true
        }
    }

    private fun clearSearchView() {
        searchView.setQuery("", false)
        searchView.isIconified = true
    }

    private fun processListLoading(uiState: CosmeticsUiState) {
        when (uiState) {
            is CosmeticsUiState.Loading -> {
                setIsLoading(true)
            }
            is CosmeticsUiState.Success -> {
                setIsLoading(false)
                adapter.cosmetics = uiState.cosmetics
            }
            is CosmeticsUiState.Error -> {
                setIsLoading(false)
                showMessage("Can't load cosmetics list")
                uiState.exc.printStackTrace()
            }
            is CosmeticsUiState.NoResults -> {
                setIsLoading(false)
                showMessage("No results!")
            }
            else -> {
                setIsLoading(false)
            }
        }
    }

    private fun setIsLoading(loading: Boolean) {
        with(binding) {
            swipeRefreshLayout.isRefreshing = loading
        }
    }

    private fun showMessage(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun navigateToDetailCosmetic(cosmetic: Cosmetic) {
        val action =
            CosmeticsListFragmentDirections.actionCosmeticsListFragmentToDetailCosmeticFragment2(
                cosmetic
            )
        findNavController().navigate(action)
    }
}