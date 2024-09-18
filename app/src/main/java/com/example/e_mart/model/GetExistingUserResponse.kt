package com.example.e_mart.model

import com.google.gson.annotations.SerializedName

data class GetExistingUserResponse(
    @SerializedName("status")
    val Status : Int,

    @SerializedName("message")
    val Message : String,

    @SerializedName("user")
    val User_Info : User?
)

data class User(
    @SerializedName("user_id")
    val User_Id : Int,

    @SerializedName("full_name")
    val Full_Name : String,

    @SerializedName("mobile_no")
    val Mob_No : String,

    @SerializedName("email_id")
    val Email_Id : String
)
