package com.example.e_mart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_mart.model.ApiClient
import com.example.e_mart.model.ApiService
import com.example.e_mart.model.GetExistingUser
import com.example.e_mart.model.GetExistingUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {
    private val apiService: ApiService = ApiClient.retrofit.create(ApiService::class.java)
    private val _existingUser = MutableLiveData<GetExistingUserResponse>()
    val existingUser : LiveData<GetExistingUserResponse> = _existingUser

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun existUser(email: String, password: String) {
        val existUser = GetExistingUser(email, password)
        val call: Call<GetExistingUserResponse> = apiService.getexistingUser(existUser)

        call.enqueue(object : Callback<GetExistingUserResponse> {
            override fun onResponse(
                call: Call<GetExistingUserResponse>,
                response: Response<GetExistingUserResponse>
            ) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    if (userResponse != null) {
                        _existingUser.value = userResponse
                        _message.value = "Success"
                    } else {
                        _message.value = "Empty User"
                    }
                } else {
                    _message.value = "Try Again"
                }
            }

            override fun onFailure(call: Call<GetExistingUserResponse>, t: Throwable) {
                t.printStackTrace()
                _message.value = "An error occurred: ${t.localizedMessage}"
            }
        })
    }
}