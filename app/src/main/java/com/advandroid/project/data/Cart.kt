package com.advandroid.project.data

import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class Cart(
    var date: LocalDate,
    var listOfSelectedProducts: MutableList<SelectedProduct> = ArrayList<SelectedProduct>(),
    var id: String = UUID.randomUUID().toString()
) {
}