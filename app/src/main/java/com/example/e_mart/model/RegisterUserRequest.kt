package com.example.e_mart.model

import com.google.gson.annotations.SerializedName

data class RegisterUserRequest(
    @SerializedName("full_name")
    val Full_Name: String,

    @SerializedName("mobile_no")
    val Mob_No : Long,

    @SerializedName("email_id")
    val Email_Id : String,

    @SerializedName("password")
    val Finger_Print : String
)
