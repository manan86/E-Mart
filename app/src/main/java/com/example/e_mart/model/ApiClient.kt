package com.example.e_mart.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://apolisrises.co.in/myshop/index.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}