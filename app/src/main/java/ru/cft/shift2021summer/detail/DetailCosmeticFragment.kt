package ru.cft.shift2021summer.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ru.cft.shift2021summer.CosmeticsApplication
import ru.cft.shift2021summer.CosmeticsRepository
import ru.cft.shift2021summer.R
import ru.cft.shift2021summer.databinding.FragmentDetailCosmeticBinding

class DetailCosmeticFragment : Fragment() {
    private lateinit var binding: FragmentDetailCosmeticBinding

    private val detailCosmeticViewModel: DetailCosmeticViewModel by viewModels {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: DetailCosmeticFragmentArgs by navArgs()
        detailCosmeticViewModel.loadCosmetic(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_cosmetic, container, false)
        binding = FragmentDetailCosmeticBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailCosmeticViewModel.cosmetic.observe(
            viewLifecycleOwner, {
                bindCosmetic()
            }
        )
    }

    private fun bindCosmetic() {
        detailCosmeticViewModel.cosmetic.value?.let {
            with(binding){
                cosmeticName.text = it.name
                cosmeticBrand.text = it.brand
                cosmeticPrice.text = context?.getString(R.string.price, it.price) ?: "Unknown"
                cosmeticStarRating.text = it.starRating
            }
        }
    }
}
