package com.advandroid.project.data

// singleton class
class Datasource {
    var cartSelectedProductsList: MutableList<SelectedProduct> = mutableListOf()
    var currentUser:User?=null

    companion object {
        @Volatile
        private lateinit var instance: Datasource

        fun getInstance(): Datasource {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = Datasource()
                }
                return instance
            }
        }
    }

    fun setCart(selectedProducts: MutableList<SelectedProduct>) {
        cartSelectedProductsList = selectedProducts
    }

    fun getCart(): MutableList<SelectedProduct> {
        return cartSelectedProductsList
    }

    fun getTotal(): Double {
        var totalToPay = 0.0
        cartSelectedProductsList.forEach{
            totalToPay += it.totalPrice
        }

        return String.format("%.2f", totalToPay).toDouble()
    }

    fun addCartItem(product: SelectedProduct) {
        cartSelectedProductsList.add(product)
    }

    fun removeCartItem(position: Int) {
        cartSelectedProductsList.removeAt(position)
    }

    fun changeItemQuantity(position: Int,change:Int){
        cartSelectedProductsList[position].qty+=change
    }

    fun logedAsUser(user:User){
        currentUser = user
    }
    fun logout(){
        currentUser= null
        cartSelectedProductsList.clear()
    }

}
