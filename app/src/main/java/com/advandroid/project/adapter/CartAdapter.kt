package com.advandroid.project.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.advandroid.project.MainActivity
import com.advandroid.project.data.CartRepository
import com.advandroid.project.data.Datasource
import com.advandroid.project.data.Product
import com.advandroid.project.data.SelectedProduct
import com.advandroid.project.databinding.ItemCartRowLayoutBinding
import com.advandroid.project.databinding.ItemRowLayoutBinding
import com.squareup.picasso.Picasso

class CartAdapter(context: Context, var dataSource: MutableList<SelectedProduct>) :
    ArrayAdapter<SelectedProduct>(context, 0, dataSource) {
    internal var data = mutableListOf<SelectedProduct>()
    private val TAG = "CartAdapter"
    lateinit var itemCartRowBinding: ItemCartRowLayoutBinding
    lateinit var datasource: Datasource

    init {
        data = dataSource
    }

    override fun getItem(position: Int): SelectedProduct {
        Log.d(TAG, "getItem[${position}]:  : ${data[position]}")
        return data[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // get binding variables
        var itemView = convertView
        itemCartRowBinding =
            ItemCartRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        itemView = itemCartRowBinding.root
        datasource = Datasource.getInstance()

        val product: SelectedProduct = getItem(position)
        Log.d(TAG, "getView - item - : $product")


        itemCartRowBinding.productTitle.text = product.title
        itemCartRowBinding.productPrice.text = product.totalPrice.toString()
        Picasso.get().load(product.image).into(itemCartRowBinding.productImage)
        itemCartRowBinding.tvQty.text = product.qty.toString()
        itemCartRowBinding.btnDecreaseQty.setOnClickListener {
            if (product.qty > 1) {
                product.qty--
                itemCartRowBinding.tvQty.text = product.qty.toString()
            }
        }
        itemCartRowBinding.btnIncreaseQty.setOnClickListener {
            product.qty++
            itemCartRowBinding.tvQty.text = product.qty.toString()
        }
        itemCartRowBinding.btnRemove.setOnClickListener {
            datasource.removeCartItem(position)
            CartRepository(context).deleteCartItem(product.id)
            notifyDataSetChanged()
        }

        // Return the completed view to render on screen
        return itemView!!
    }
}