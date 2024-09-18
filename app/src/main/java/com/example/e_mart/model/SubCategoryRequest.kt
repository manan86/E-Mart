package com.example.e_mart.model

import com.google.gson.annotations.SerializedName

data class SubCategoryRequest(
    @SerializedName("category_id")
    val category_id : Int
)
