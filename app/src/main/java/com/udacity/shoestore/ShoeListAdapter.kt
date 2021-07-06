package com.udacity.shoestore

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe

class ShoeListAdapter(
    private val shoes: List<Shoe>,
    private val listener: ShoeListClickListener
) :
    RecyclerView.Adapter<ShoeListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ShoeItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ShoeItemBinding = ShoeItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context

        holder.binding.apply {
            val shoe = shoes[position]

            tvName.text = shoe.name
            tvCompany.text = context.getString(R.string.company_label, shoe.company)
            tvSize.text = context.getString(R.string.size_label, shoe.size.toString())
            tvDescription.text = shoe.description

            root.setOnClickListener {
                listener.onViewItemClick(shoe)
            }

            executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return shoes.size
    }
}