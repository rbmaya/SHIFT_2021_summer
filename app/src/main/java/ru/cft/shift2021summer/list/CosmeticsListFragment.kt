package ru.cft.shift2021summer.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.cft.shift2021summer.CosmeticsApplication
import ru.cft.shift2021summer.CosmeticsRepository
import ru.cft.shift2021summer.R
import ru.cft.shift2021summer.databinding.FragmentCosmeticListBinding

class CosmeticsListFragment : Fragment() {
    private lateinit var binding: FragmentCosmeticListBinding
    private lateinit var cosmeticsRecyclerView: RecyclerView

    private val adapter = CosmeticsListAdapter {
        navigateToDetailCosmetic(id = it.id)
    }

    private val cosmeticsListViewModel: CosmeticsListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                modelClass
                    .getConstructor(
                        CosmeticsRepository::class.java,
                    ).newInstance(
                        (activity?.application as CosmeticsApplication).cosmeticsRepository
                    )
        }
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