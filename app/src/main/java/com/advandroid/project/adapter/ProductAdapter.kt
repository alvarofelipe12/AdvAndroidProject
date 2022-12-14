package com.advandroid.project.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.advandroid.project.MainActivity
import com.advandroid.project.data.Product
import com.advandroid.project.databinding.ItemRowLayoutBinding
import com.squareup.picasso.Picasso
import java.util.*


class ProductAdapter(context: Context, var dataSource: ArrayList<Product>) :
    ArrayAdapter<Product>(context, 0, dataSource) {

    private val TAG = "ProductAdapter"
    lateinit var itemRowBinding: ItemRowLayoutBinding
    private var originalList: ArrayList<Product> = dataSource
    private var displayedList: ArrayList<Product> = dataSource

    override fun getItem(position: Int): Product {
        return displayedList[position]
    }

    override fun getCount(): Int {
        return displayedList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // get binding variables
        var itemView = convertView
        itemRowBinding = ItemRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        itemView = itemRowBinding.root

        val product: Product = getItem(position)
        Log.d(TAG, "getView - item - : ${product.title}")


        itemRowBinding.productTitle.setText(product.title)
        itemRowBinding.productPrice.setText(product.price.toString())
        Picasso.get().load(product.image).into(itemRowBinding.productImage)
        itemRowBinding.productContainer.setOnClickListener {
            (context as MainActivity?)!!.goToDetail(product, it.id)
        }

        // Return the completed view to render on screen
        return itemView!!
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(constraint: CharSequence?, filterResults: FilterResults) {
                displayedList =
                    (filterResults.values as ArrayList<Product>?)!!
                notifyDataSetChanged()
            }

            override fun performFiltering(constraint: CharSequence?): FilterResults? {
                var constraintHolder = constraint
                val filterResults: FilterResults =
                    FilterResults()
                val filteredArrList: ArrayList<Product> = ArrayList()
                if (originalList == null) {
                    originalList =
                        ArrayList(displayedList)
                }
                if (constraintHolder == null || constraintHolder.isEmpty()) {
                    filterResults.count = originalList.size
                    filterResults.values = originalList
                } else {
                    constraintHolder = constraintHolder.toString().lowercase(Locale.getDefault())
                    for (i in originalList.indices) {
                        val data: String = originalList[i].title
                        if (data.lowercase(Locale.getDefault())
                                .contains(constraintHolder.toString())
                        ) {
                            filteredArrList.add(
                                originalList[i]
                            )
                        }
                    }
                    filterResults.count = filteredArrList.size
                    filterResults.values = filteredArrList
                }
                return filterResults
            }
        }
    }
}