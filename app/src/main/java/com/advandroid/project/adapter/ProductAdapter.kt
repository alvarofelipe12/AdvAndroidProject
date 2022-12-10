package com.advandroid.project.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.advandroid.project.data.Product
import com.advandroid.project.databinding.ItemRowLayoutBinding
import com.squareup.picasso.Picasso


class ProductAdapter(context: Context, var dataSource: ArrayList<Product>) :
    ArrayAdapter<Product>(context, 0, dataSource) {

    internal var data = ArrayList<Product>()
    internal val TAG = "ProductAdapter"

    init {
        data = dataSource
    }

    override fun getItem(position: Int): Product {
        Log.d(TAG, "getItem[${position}]:  : ${data[position]}")
        return data[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // get binding variables
        lateinit var itemRowBinding: ItemRowLayoutBinding
        var itemView = convertView
        itemRowBinding = ItemRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        itemView = itemRowBinding.root

        val product: Product = getItem(position)
        Log.d(TAG, "getView - item - : ${product}")


        itemRowBinding.productTitle.setText(product.title)
        itemRowBinding.productPrice.setText(product.price.toString())
        Picasso.get().load(product.image).into(itemRowBinding.productImage);
        itemRowBinding.productContainer.setOnClickListener {
            Log.d(TAG, "Row number ${position} was clicked.")
            // call a function in the main activity
            // (context as MainActivity?)!!.doSomething()...
        }

        // Return the completed view to render on screen
        return itemView!!
    }
}