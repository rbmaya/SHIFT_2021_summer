package ru.cft.shift2021summer.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.shift2021summer.R
import ru.cft.shift2021summer.databinding.FragmentCosmeticListBinding

@AndroidEntryPoint
class CosmeticsListFragment : Fragment() {
    private lateinit var binding: FragmentCosmeticListBinding
    private lateinit var cosmeticsRecyclerView: RecyclerView

    private val adapter = CosmeticsListAdapter {
        navigateToDetailCosmetic(id = it.id)
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
        cosmeticsListViewModel.cosmeticsList.observe(viewLifecycleOwner,
            {
                adapter.cosmetics = it
            })
    }

    private fun navigateToDetailCosmetic(id: Long) {
        val action =
            CosmeticsListFragmentDirections.actionCosmeticsListFragmentToDetailCosmeticFragment2(id)
        findNavController().navigate(action)
    }
}