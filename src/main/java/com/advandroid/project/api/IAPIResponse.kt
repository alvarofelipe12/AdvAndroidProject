package com.advandroid.project.api

import com.advandroid.project.data.Product

import retrofit2.http.GET

interface IAPIResponse {
    @GET("/products")
    suspend fun getAllProduct():List<Product>
}