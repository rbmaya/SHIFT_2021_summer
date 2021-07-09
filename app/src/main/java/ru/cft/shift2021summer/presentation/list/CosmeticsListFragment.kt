package ru.cft.shift2021summer.presentation.list

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.cft.shift2021summer.R
import ru.cft.shift2021summer.databinding.FragmentCosmeticListBinding
import ru.cft.shift2021summer.domain.Cosmetic


@AndroidEntryPoint
class CosmeticsListFragment : Fragment() {
    private lateinit var binding: FragmentCosmeticListBinding
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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cosmeticsListViewModel.loadCosmetics(false)
        binding.swipeRefreshLayout.setOnRefreshListener {
            cosmeticsListViewModel.loadCosmetics(true)
        }
        lifecycleScope.launch {
            cosmeticsListViewModel.uiState.flowWithLifecycle(lifecycle)
                .collect {
                    processListLoading(it)
                }
        }
        cosmeticsListViewModel.openDetailCosmeticEvent.observe(viewLifecycleOwner, {
            navigateToDetailCosmetic(it)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                cosmeticsListViewModel.queryTextChanged(newText)
                Log.d("#@@!", "onQueryTextChange")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("#@@!", "onQueryTextSubmit")
                return true
            }
        })
    }

    private fun processListLoading(uiState: CosmeticsListViewModel.CosmeticsListUiState){
        when (uiState){
            is CosmeticsListViewModel.CosmeticsListUiState.Loading -> {
                setIsLoading(true)
            }
            is CosmeticsListViewModel.CosmeticsListUiState.Success -> {
                setIsLoading(false)
                adapter.cosmetics = uiState.cosmetics
            }
            is CosmeticsListViewModel.CosmeticsListUiState.Error -> {
                setIsLoading(false)
                showError(uiState.message)
                uiState.exc.printStackTrace()
            }
            else -> {
                setIsLoading(false)
            }
        }
    }

    private fun setIsLoading(loading: Boolean){
        with(binding){
            swipeRefreshLayout.isRefreshing = loading
        }
    }

    private fun showError(message: String){
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun navigateToDetailCosmetic(cosmetic: Cosmetic) {
        val action =
            CosmeticsListFragmentDirections.actionCosmeticsListFragmentToDetailCosmeticFragment2(cosmetic)
        findNavController().navigate(action)
    }
}