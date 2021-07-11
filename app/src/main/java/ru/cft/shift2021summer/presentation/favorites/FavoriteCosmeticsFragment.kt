package ru.cft.shift2021summer.presentation.favorites

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
import ru.cft.shift2021summer.databinding.FragmentFavoriteCosmeticsBinding
import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.presentation.list.CosmeticsListAdapter
import ru.cft.shift2021summer.presentation.list.CosmeticsListFragmentDirections
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
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarListFavorites)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoritesCosmeticViewModel.loadCosmetics()
        lifecycleScope.launch {
            favoritesCosmeticViewModel.uiState.flowWithLifecycle(lifecycle)
                .collect {
                    processListLoading(it)
                }
        }
        favoritesCosmeticViewModel.openDetailCosmeticEvent.observe(viewLifecycleOwner, {
            navigateToDetailCosmetic(it)
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
            else -> {}
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