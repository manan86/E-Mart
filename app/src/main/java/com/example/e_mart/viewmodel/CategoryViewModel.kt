package com.example.e_mart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_mart.model.ApiClient
import com.example.e_mart.model.ApiService
import com.example.e_mart.model.Category
import com.example.e_mart.model.CategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel : ViewModel() {

    private val apiService = ApiClient.retrofit.create(ApiService::class.java)

    private val _categoryView = MutableLiveData<List<Category>>()
    val categoryView: LiveData<List<Category>> = _categoryView

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun getCategory() {
        val call: Call<CategoryResponse> = apiService.getCategoryInfo()  // Updated call type

        call.enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val responseBody = response.body()!!
                    if (responseBody.status == 0) { // Assuming status 0 is successful
                        _categoryView.value = responseBody.categories  // Set the response to the LiveData
                    } else {
                        _message.value = "Failed to retrieve categories: ${responseBody.message}"
                    }
                } else {
                    _message.value = "Failed to retrieve categories: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                t.printStackTrace()
                _message.value = "An error occurred: ${t.localizedMessage}"
            }
        })
    }
}
