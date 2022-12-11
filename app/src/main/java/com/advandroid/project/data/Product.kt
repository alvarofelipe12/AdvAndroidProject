package com.advandroid.project.data

import java.util.*

open class Product(
    var title: String,
    var price: Double,
    var category: String,
    var description: String,
    var image: String,
    var id: String = UUID.randomUUID().toString()
): java.io.Serializable {
    override fun toString(): String {
        return "Product(title='$title', price=$price, category='$category', description='$description', image='$image', id='$id')"
    }
}