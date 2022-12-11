package com.advandroid.project.data

import java.util.*

class SelectedProduct(
    var prodTitle: String,
    var prodPrice: Double,
    var prodCategory: String,
    var prodDescription: String,
    var prodImage: String,
    var qty: Int,
    var totalPrice: Double = qty * prodPrice,
    var prodId: String = UUID.randomUUID().toString()
) : Product(
    title = prodTitle,
    price = prodPrice,
    category = prodCategory,
    description = prodDescription,
    image = prodImage,
    id = prodId
) {
}