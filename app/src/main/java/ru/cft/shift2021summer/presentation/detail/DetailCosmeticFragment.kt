package ru.cft.shift2021summer.presentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.shift2021summer.R
import ru.cft.shift2021summer.databinding.FragmentDetailCosmeticBinding
import ru.cft.shift2021summer.domain.Cosmetic

@AndroidEntryPoint
class DetailCosmeticFragment : Fragment() {
    private lateinit var binding: FragmentDetailCosmeticBinding
    private lateinit var openLinkCosmeticLauncher: ActivityResultLauncher<Intent>
    private val detailCosmeticViewModel: DetailCosmeticViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openLinkCosmeticLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
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
        val args: DetailCosmeticFragmentArgs by navArgs()
        bindCosmetic(args.cosmetic)
        detailCosmeticViewModel.openCosmeticLinkEvent.observe(viewLifecycleOwner, {
            openCosmeticLink(args.cosmetic.productLink)
        })
    }

    private fun openCosmeticLink(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        openLinkCosmeticLauncher.launch(intent)
    }

    private fun bindCosmetic(cosmetic: Cosmetic) {
        with(binding) {
            collapsingToolbar.title = cosmetic.name
            Picasso.with(context).load(cosmetic.imageLink).fit().centerCrop()
                .into(cosmeticImage)
            cosmeticBrand.text = cosmetic.brand
            cosmeticPrice.text = requireContext().getString(R.string.price, cosmetic.price)
            cosmeticStarRating.text = cosmetic.starRating
            cosmeticDescription.text = cosmetic.description
            buttonBuy.setOnClickListener {
                detailCosmeticViewModel.openCosmeticLink()
            }
        }
    }
}
