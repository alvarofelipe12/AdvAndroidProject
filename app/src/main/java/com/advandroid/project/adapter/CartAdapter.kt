package com.advandroid.project.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.advandroid.project.R
import com.advandroid.project.data.CartRepository
import com.advandroid.project.data.Datasource
import com.advandroid.project.data.SelectedProduct
import com.advandroid.project.databinding.ItemCartRowLayoutBinding
import com.advandroid.project.fragment.CartFragment
import com.squareup.picasso.Picasso

class CartAdapter(fragment: CartFragment, dataSource: MutableList<SelectedProduct>) :
    ArrayAdapter<SelectedProduct>(fragment.requireContext(), 0, dataSource) {
    internal var data = mutableListOf<SelectedProduct>()
    private val TAG = "CartAdapter"
    lateinit var itemCartRowBinding: ItemCartRowLayoutBinding
    lateinit var dataSource: Datasource
    private val fragment = fragment

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
        dataSource = Datasource.getInstance()

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
            deleteItem(position, product.id)
        }

        // Return the completed view to render on screen
        return itemView!!
    }

    private fun deleteItem(position: Int, id: String) {
        //ask for confirmation
        val confirmDialog = AlertDialog.Builder(context)
        confirmDialog.setTitle(context.getString(R.string.delete))
        confirmDialog.setMessage(context.getString(R.string.confirmation_delete))
        confirmDialog.setNegativeButton(context.getString(R.string.cancel)) { dialogInterface, i ->
            notifyDataSetChanged()
            dialogInterface.dismiss()
        }
        confirmDialog.setPositiveButton(context.getString(R.string.yes)) { dialogInterface, i ->
            dataSource.removeCartItem(position)
            CartRepository(context).deleteCartItem(id)
            // updating price in pay button
            fragment.btnPayNow.text = "Pay now ${dataSource.getTotal()}"
            notifyDataSetChanged()
        }
        confirmDialog.show()
    }
}