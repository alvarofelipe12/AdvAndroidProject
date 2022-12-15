package com.advandroid.project.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.advandroid.project.R
import com.advandroid.project.data.CartRepository
import com.advandroid.project.data.Datasource
import com.advandroid.project.data.SelectedProduct
import com.advandroid.project.databinding.FragmentCart2Binding
import com.advandroid.project.fragment.CartFragment
import com.squareup.picasso.Picasso


class Cart2RecyclerViewAdapter(
    private val values: List<SelectedProduct>,
    context: Context,
    fragment: CartFragment
) : RecyclerView.Adapter<Cart2RecyclerViewAdapter.ViewHolder>() {

    private val ctx = context
    val frag = fragment
    lateinit var dataSource: Datasource

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentCart2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataSource = Datasource.getInstance()
        val item = values[position]
        holder.title.text = item.title
        holder.price.text = item.totalPrice.toString()
        Picasso.get().load(item.image).into(holder.image)
        holder.qty.text = item.qty.toString()
        holder.decreaseQty.setOnClickListener {
            if (item.qty > 1) {
                item.qty--
                CartRepository(ctx).changeItemQuantity(
                    item.id,
                    dataSource.currentUser!!.uid,
                    item.qty
                )
//                dataSource.changeItemQuantity(position, item.qty)
                holder.qty.text = item.qty.toString()
            }
        }
        holder.increaseQty.setOnClickListener {
            item.qty++
            CartRepository(ctx).changeItemQuantity(item.id, dataSource.currentUser!!.uid, item.qty)
//            dataSource.changeItemQuantity(position, item.qty)
            holder.qty.text = item.qty.toString()
        }
        holder.remove.setOnClickListener {
            deleteItem(position, item.id)
        }
//        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentCart2Binding) : RecyclerView.ViewHolder(binding.root) {
        val qty: TextView = binding.tvQtyCart
        val price: TextView = binding.productPrice
        val title: TextView = binding.productTitle
        val image: ImageView = binding.productImage
        val decreaseQty: Button = binding.btnDecreaseQty
        val increaseQty: Button = binding.btnIncreaseQty
        val remove: Button = binding.btnRemove
        override fun toString(): String {
            return "ViewHolder(qty=$qty, price=$price, title=$title, image=$image, decreaseQty=$decreaseQty, increaseQty=$increaseQty, remove=$remove)"
        }

    }

    private fun deleteItem(position: Int, id: String) {
        //ask for confirmation
        val confirmDialog = AlertDialog.Builder(ctx)
        confirmDialog.setTitle(ctx.getString(R.string.delete))
        confirmDialog.setMessage(ctx.getString(R.string.confirmation_delete))
        confirmDialog.setNegativeButton(ctx.getString(R.string.cancel)) { dialogInterface, i ->
            notifyDataSetChanged()
            dialogInterface.dismiss()
        }
        confirmDialog.setPositiveButton(ctx.getString(R.string.yes)) { dialogInterface, i ->
            dataSource.removeCartItem(position)
            CartRepository(ctx).deleteCartItem(id, dataSource.currentUser!!.uid)
//            data.removeAt(position)
            // updating price in pay button
            frag.btnPayNow.text = "Pay now ${dataSource.getTotal()}"
            notifyDataSetChanged()
        }
        confirmDialog.show()
    }

}