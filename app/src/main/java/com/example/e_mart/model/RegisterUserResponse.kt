package com.example.e_mart.model

import com.google.gson.annotations.SerializedName

data class RegisterUserResponse(
    @SerializedName("status")
    val Status_Code : Int,

    @SerializedName("message")
    val Message : String
)
