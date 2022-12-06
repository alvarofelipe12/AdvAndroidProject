package com.advandroid.project.data

import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class Cart(
    var date: LocalDate,
    var products: MutableList<Product> = ArrayList<Product>(),
    var id: String = UUID.randomUUID().toString()
) {
}