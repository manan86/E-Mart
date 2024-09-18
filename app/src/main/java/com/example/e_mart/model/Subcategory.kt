package com.example.e_mart.model

import com.google.gson.annotations.SerializedName

data class Subcategory(

    @SerializedName("category_id")
    val category_id: String,

    @SerializedName("is_active")
    val is_active: String,

    @SerializedName("subcategory_id")
    val subcategory_id: String,

    @SerializedName("subcategory_image_url")
    val subcategory_image_url: String,

    @SerializedName("subcategory_name")
    val subcategory_name: String
)