package com.example.e_mart.model

import com.google.gson.annotations.SerializedName

data class ItemsResponse(

    @SerializedName("message")
    val message: String,

    @SerializedName("products")
    val products: List<Product>,

    @SerializedName("status")
    val status: Int
)