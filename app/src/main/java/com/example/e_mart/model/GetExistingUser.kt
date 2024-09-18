package com.example.e_mart.model

import com.google.gson.annotations.SerializedName

data class GetExistingUser(
    @SerializedName("email_id")
    val Email_Id : String,

    @SerializedName("password")
    val Finger_Print : String
)
