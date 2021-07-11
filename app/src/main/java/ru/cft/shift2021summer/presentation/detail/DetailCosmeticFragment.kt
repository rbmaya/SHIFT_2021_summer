package ru.cft.shift2021summer.presentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.shift2021summer.R
import ru.cft.shift2021summer.databinding.FragmentDetailCosmeticBinding
import ru.cft.shift2021summer.domain.Cosmetic
import javax.inject.Inject

@AndroidEntryPoint
class DetailCosmeticFragment : Fragment() {
    private lateinit var binding: FragmentDetailCosmeticBinding
    private lateinit var openLinkCosmeticLauncher: ActivityResultLauncher<Intent>

    @Inject
    lateinit var detailViewModelFactory: DetailCosmeticViewModel.Factory

    private val detailCosmeticViewModel: DetailCosmeticViewModel by viewModels {
        val args: DetailCosmeticFragmentArgs by navArgs()
        DetailCosmeticViewModel.provideFactory(detailViewModelFactory, args.cosmetic)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openLinkCosmeticLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_cosmetic, container, false)
        binding = FragmentDetailCosmeticBinding.bind(view)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarDetail)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindCosmetic(detailCosmeticViewModel.cosmetic)
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        detailCosmeticViewModel.openCosmeticLinkEvent.observe(viewLifecycleOwner, {
            openCosmeticLink(detailCosmeticViewModel.cosmetic.productLink)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.details_menu, menu)
        val favoriteItem = menu.findItem(R.id.add_to_favorites)
        favoriteItem.icon = if (detailCosmeticViewModel.cosmetic.isFavorite){
            AppCompatResources.getDrawable(requireContext(), R.drawable.ic_favorite_pressed)
        } else {
            AppCompatResources.getDrawable(requireContext(), R.drawable.ic_favorite_unpressed)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.add_to_favorites -> {
                val cosmetic = detailCosmeticViewModel.cosmetic
                val iconPressed = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_favorite_pressed)
                val iconUnpressed = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_favorite_unpressed)
                if (cosmetic.isFavorite){
                    Log.d("@@!%", "iconPressed")
                    detailCosmeticViewModel.changeIsFavoriteCosmetic(cosmetic.id, false)
                    item.icon = iconUnpressed
                } else {
                    Log.d("@@!%", "iconUnpressed")
                    detailCosmeticViewModel.changeIsFavoriteCosmetic(cosmetic.id, true)
                    item.icon = iconPressed
                }
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
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
