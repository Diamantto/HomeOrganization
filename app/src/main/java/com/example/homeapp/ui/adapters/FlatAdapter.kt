package com.example.homeapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeapp.R
import com.example.homeapp.data.db.entities.Flat
import com.example.homeapp.databinding.ItemFlatBinding

class FlatAdapter : RecyclerView.Adapter<FlatAdapter.FlatViewHolder>() {
    var onItemClickCallBack: (Int) -> Unit = { _ -> }
    var items: List<Flat?> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class FlatViewHolder(val binding: ItemFlatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlatViewHolder {
        val viewHolder = FlatViewHolder(
            ItemFlatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        viewHolder.itemView.setOnClickListener {
            onItemClickCallBack(items[viewHolder.bindingAdapterPosition]?.flatId ?: 0)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: FlatViewHolder, position: Int) {
        holder.binding.apply {
            tvItemFlat.text = root.context.getString(
                R.string.flat_number_template,
                items[holder.bindingAdapterPosition]?.number
            )
            tvItemPersonQuantity.text = root.context.getString(
                R.string.flat_quantity_person_template,
                items[holder.bindingAdapterPosition]?.personQuantity
            )
            tvItemRoomQuantity.text = root.context.getString(
                R.string.flat_quantity_room_template,
                items[holder.bindingAdapterPosition]?.roomQuantity
            )
            tvItemDescription.text = root.context.getString(
                R.string.flat_description_template,
                items[holder.bindingAdapterPosition]?.flatDescription
            )
            tvItemFlatId.text = root.context.getString(
                R.string.flat_id_template,
                items[holder.bindingAdapterPosition]?.flatId
            )
        }
    }

    override fun getItemCount(): Int = items.size
}