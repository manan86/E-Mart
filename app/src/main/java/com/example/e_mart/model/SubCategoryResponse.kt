package com.example.e_mart.model

import com.google.gson.annotations.SerializedName

data class SubCategoryResponse(

    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int,

    @SerializedName("subcategories")
    val subcategories: List<Subcategory>
)