package com.udacity.shoestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.shoe_item.view.*

class ShoeListAdapter(private var dataSet: List<Shoe>) :
    RecyclerView.Adapter<ShoeListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.tv_name
        val company: TextView = itemView.tv_company
        val size: TextView = itemView.tv_size
        val description: TextView = itemView.tv_description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.shoe_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context

        holder.apply {
            name.text = dataSet[position].name
            company.text = context.getString(R.string.company_label, dataSet[position].company)
            size.text =context.getString(R.string.size_label,  dataSet[position].size.toString())
            description.text = dataSet[position].description
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}