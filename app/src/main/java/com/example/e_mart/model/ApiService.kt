package com.example.e_mart.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Content-type: application/json")
    @POST("User/register")
    fun registerNewUser(
        @Body apr1 : RegisterUserRequest
    ):Call<RegisterUserResponse>

    @Headers("Content-type: application/json")
    @POST("User/auth")
    fun getexistingUser(
        @Body apr2 : GetExistingUser
    ): Call<GetExistingUserResponse>

    @GET("Category")
    fun getCategoryInfo(): Call<CategoryResponse>

    @GET("SubCategory")
    fun getSubcategory(
        @Query("category_id") categoryID : Int
    ): Call<SubCategoryResponse>

    @GET("SubCategory/products/{sub_category_id}")
    fun getItemsForSubcategory(@Path("sub_category_id") subCategoryId: Int
    ): Call<ItemsResponse>

}
