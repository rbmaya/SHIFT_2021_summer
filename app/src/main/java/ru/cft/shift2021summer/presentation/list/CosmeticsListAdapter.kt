package ru.cft.shift2021summer.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.R
import ru.cft.shift2021summer.databinding.ItemCosmeticsListBinding

class CosmeticsListAdapter(private val onItemClick: (Cosmetic) -> Unit) :
    RecyclerView.Adapter<CosmeticsListAdapter.CosmeticHolder>() {

    var cosmetics: List<Cosmetic> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CosmeticHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCosmeticsListBinding.inflate(layoutInflater, parent, false)
        return CosmeticHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: CosmeticHolder, position: Int) {
        holder.bind(cosmetics[position])
    }

    override fun getItemCount(): Int = cosmetics.size

    class CosmeticHolder(
        private val itemBinding: ItemCosmeticsListBinding,
        private val onItemClick: (Cosmetic) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(cosmetic: Cosmetic) {
            with(itemBinding) {
                progressBar.isVisible = true
                Picasso.with(itemView.context).load(cosmetic.imageLink).fit().centerCrop()
                    .into(cosmeticImage, object : Callback{
                        override fun onSuccess() {
                            progressBar.isVisible = false 
                        }
                        override fun onError() {}
                    })
                nameLabel.text = cosmetic.name
                cosmeticPrice.text = itemView.context.getString(R.string.price, cosmetic.price.toString())
            }
            itemView.setOnClickListener {
                onItemClick(cosmetic)
            }
        }
    }
}