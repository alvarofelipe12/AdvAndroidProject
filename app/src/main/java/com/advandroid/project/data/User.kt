package com.advandroid.project.data

import java.util.UUID

data class User(
    var email: String = "",
    var password: String = "",
    var cart: Cart,
    var id: String = UUID.randomUUID().toString(),
) {}