package com.example.e_mart.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("category_id")
    val id: Int,

    @SerializedName("category_image_url")
    val image: String,

    @SerializedName("category_name")
    val name: String,

    @SerializedName("is_active")
    val is_active: String
)
