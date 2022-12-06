package com.advandroid.project.data

import java.util.*

class Product(
    var title: String,
    var price: Double,
    var category: String,
    var description: String,
    var image: String,
    var id: String = UUID.randomUUID().toString()
) {
}