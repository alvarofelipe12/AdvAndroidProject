package com.advandroid.project.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.advandroid.project.data.SelectedProduct
import com.advandroid.project.databinding.FragmentReceiptBinding

class ReceiptRecyclerViewAdapter(
    private val values: List<SelectedProduct>
) : RecyclerView.Adapter<ReceiptRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentReceiptBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text =
            if (item.title.length > 15) item.title.take(15) + "..." else item.title
        holder.price.text = item.totalPrice.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentReceiptBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.tvTitleReceipt
        val price: TextView = binding.tvPriceReceipt
        override fun toString(): String {
            return "ViewHolder(title=$title, price=$price)"
        }

    }

}