package com.example.homeapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeapp.R
import com.example.homeapp.data.db.entities.Person
import com.example.homeapp.databinding.ItemPersonBinding

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    var onItemClickCallBack: (Int) -> Unit = { _ -> }
    var items: List<Person?> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class PersonViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val viewHolder = PersonViewHolder(
            ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        viewHolder.itemView.setOnClickListener {
            onItemClickCallBack(items[viewHolder.bindingAdapterPosition]?.personId ?: 0)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.binding.apply {
            tvItemPersonSurname.text = root.context.getString(
                R.string.person_surname_template,
                items[holder.bindingAdapterPosition]?.surname
            )
            tvItemPersonName.text = root.context.getString(
                R.string.person_name_template,
                items[holder.bindingAdapterPosition]?.name
            )
            tvItemPersonFlat.text = root.context.getString(
                R.string.flat_number_template,
                items[holder.bindingAdapterPosition]?.flatNumber
            )
            tvItemPersonPhone.text = root.context.getString(
                R.string.person_phone_template,
                items[holder.bindingAdapterPosition]?.phoneNumber.toString()
            )
            tvItemPersonId.text = root.context.getString(
                R.string.person_id_template,
                items[holder.bindingAdapterPosition]?.personId
            )
        }
    }

    override fun getItemCount(): Int = items.size
}