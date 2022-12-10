package com.example.advaproj.api

import com.example.advaproj.model.Product

import retrofit2.http.GET

interface IAPIResponse {
    @GET("/products")
    suspend fun getAllProduct():List<Product>
}