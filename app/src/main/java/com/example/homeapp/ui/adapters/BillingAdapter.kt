package com.example.homeapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeapp.R
import com.example.homeapp.data.db.entities.Billing
import com.example.homeapp.databinding.ItemBillingBinding


class BillingAdapter : RecyclerView.Adapter<BillingAdapter.BillingViewHolder>() {
    var onItemClickCallBack: (Int) -> Unit = { _ -> }
    var items: List<Billing?> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class BillingViewHolder(val binding: ItemBillingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillingViewHolder {
        val viewHolder = BillingViewHolder(
            ItemBillingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        viewHolder.itemView.setOnClickListener {
            onItemClickCallBack(items[viewHolder.bindingAdapterPosition]?.billingId ?: 0)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BillingViewHolder, position: Int) {
        holder.binding.apply {
            tvItemBilling.text = root.context.getString(
                R.string.flat_number_template,
                items[holder.bindingAdapterPosition]?.flatNumber
            )
            tvItemBillingTime.text = root.context.getString(
                R.string.billing_time_template,
                items[holder.bindingAdapterPosition]?.time?.name
            )
            tvItemBillingSum.text = root.context.getString(
                R.string.billing_sum_template,
                items[holder.bindingAdapterPosition]?.sum
            )
            tvItemBillingService.text = root.context.getString(
                R.string.billing_service_template,
                items[holder.bindingAdapterPosition]?.serviceType?.name
            )
            tvItemBillingId.text = root.context.getString(
                R.string.billing_id_template,
                items[holder.bindingAdapterPosition]?.billingId
            )
        }
    }

    override fun getItemCount(): Int = items.size
}