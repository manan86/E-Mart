package com.example.e_mart.model

import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("average_rating")
    val average_rating: String,

    @SerializedName("category_id")
    val category_id: String,

    @SerializedName("category_name")
    val category_name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("product_id")
    val product_id: String,

    @SerializedName("product_image_url")
    val product_image_url: String,

    @SerializedName("product_name")
    val product_name: String,

    @SerializedName("sub_category_id")
    val sub_category_id: String,

    @SerializedName("subcategory_name")
    val subcategory_name: String
)