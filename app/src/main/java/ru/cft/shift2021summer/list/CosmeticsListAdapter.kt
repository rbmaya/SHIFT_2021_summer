package ru.cft.shift2021summer.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.cft.shift2021summer.Cosmetic
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
                nameLabel.text = cosmetic.name
                cosmeticPrice.text = itemView.context.getString(R.string.price, cosmetic.price)
            }
            itemView.setOnClickListener {
                onItemClick(cosmetic)
            }
        }
    }
}