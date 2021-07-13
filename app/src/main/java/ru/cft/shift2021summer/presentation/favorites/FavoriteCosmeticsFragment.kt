package ru.cft.shift2021summer.presentation.favorites

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
import ru.cft.shift2021summer.databinding.FragmentFavoriteCosmeticsBinding
import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.presentation.MainActivity
import ru.cft.shift2021summer.presentation.list.CosmeticsListAdapter
import ru.cft.shift2021summer.utils.CosmeticsUiState

@AndroidEntryPoint
class FavoriteCosmeticsFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteCosmeticsBinding
    private lateinit var favoritesRecyclerView: RecyclerView

    private val favoritesCosmeticViewModel: FavoritesCosmeticViewModel by viewModels()

    private val adapter = CosmeticsListAdapter {
        favoritesCosmeticViewModel.openDetailCosmetic(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_cosmetics, container, false)
        binding = FragmentFavoriteCosmeticsBinding.bind(view)
        favoritesRecyclerView = binding.cosmeticsList
        favoritesRecyclerView.adapter = adapter
        with(activity as MainActivity) {
            setToolbar()
            setSearchOptionsListeners(createOptionsSearchListener())
            setListenerOnRemoveSearchOptions {
                favoritesCosmeticViewModel.loadCosmetics()
                closeDrawers()
            }
            setListenerOnPriceSlider(createPriceSliderListener())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoritesCosmeticViewModel.loadCosmetics()
        favoritesCosmeticViewModel.loadPriceLimits()
        lifecycleScope.launch {
            favoritesCosmeticViewModel.uiState.flowWithLifecycle(lifecycle)
                .collect {
                    processListLoading(it)
                }
        }
        favoritesCosmeticViewModel.openDetailCosmeticEvent.observe(viewLifecycleOwner, {
            navigateToDetailCosmetic(it)
        })
        favoritesCosmeticViewModel.limitPriceEvent.observe(viewLifecycleOwner, {
            processPriceLimits(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                favoritesCosmeticViewModel.queryTextChanged(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })
    }

    private fun processPriceLimits(limits: FavoritesCosmeticViewModel.LimitPriceEvent) {
        when (limits) {
            is FavoritesCosmeticViewModel.LimitPriceEvent.PriceLimits -> {
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
                favoritesCosmeticViewModel.loadCosmeticsByPrice(valueFrom = valueFrom, valueTo = valueTo)
            }
        }
    }

    private fun createOptionsSearchListener(): ExpandableListView.OnChildClickListener {
        return ExpandableListView.OnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val group = (activity as MainActivity).getSelectedSearchGroupName(groupPosition)
            val name =
                (activity as MainActivity).getSelectedSearchOptions(groupPosition, childPosition)
            when (group.lowercase()) {
                "brand" -> favoritesCosmeticViewModel.loadCosmeticsByBrand(name)
                "product type" -> favoritesCosmeticViewModel.loadCosmeticsByProductType(name)
            }
            (activity as MainActivity).closeDrawers()
            true
        }
    }

    private fun processListLoading(uiState: CosmeticsUiState) {
        when (uiState) {
            is CosmeticsUiState.Success -> {
                adapter.cosmetics = uiState.cosmetics
            }
            is CosmeticsUiState.Error -> {
                showMessage("Can't load favorite cosmetics!")
                uiState.exc.printStackTrace()
            }
            is CosmeticsUiState.NoResults -> {
                showMessage("No results!")
            }
            else -> {
            }
        }
    }

    private fun showMessage(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun navigateToDetailCosmetic(cosmetic: Cosmetic) {
        val action =
            FavoriteCosmeticsFragmentDirections.actionFavoriteCosmeticsFragmentToDetailCosmeticFragment(
                cosmetic
            )
        findNavController().navigate(action)
    }

}