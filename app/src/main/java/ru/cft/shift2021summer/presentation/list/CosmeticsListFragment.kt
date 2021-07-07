package ru.cft.shift2021summer.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
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
        cosmeticsListViewModel.loadCosmetics()
        lifecycleScope.launch {
            cosmeticsListViewModel.uiState.flowWithLifecycle(lifecycle)
                .collect {
                    when (it){
                        is CosmeticsListViewModel.CosmeticsListUiState.Loading -> {
                            setIsLoading(true)
                        }
                        is CosmeticsListViewModel.CosmeticsListUiState.Success -> {
                            setIsLoading(false)
                            adapter.cosmetics = it.cosmetics
                        }
                        is CosmeticsListViewModel.CosmeticsListUiState.Error -> {
                            showError("Can't load cosmetics list!")
                            it.exc.printStackTrace()
                        }
                    }
                }
        }

        cosmeticsListViewModel.openDetailCosmeticEvent.observe(viewLifecycleOwner, {
            navigateToDetailCosmetic(it)
        })
    }

    private fun setIsLoading(loading: Boolean){
        with(binding){
            cosmeticsRecyclerView.isVisible = !loading
            progressBar.isVisible = loading
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