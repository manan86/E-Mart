package com.example.e_mart.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_mart.model.ApiClient
import com.example.e_mart.model.ApiService
import com.example.e_mart.model.RegisterUserRequest
import com.example.e_mart.model.RegisterUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    private val apiService = ApiClient.retrofit.create(ApiService::class.java)
    private val _registratonStatus = MutableLiveData<RegisterUserResponse>()
    val registratonStatus : LiveData<RegisterUserResponse> = _registratonStatus

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun registerUser(name: String, mob: Long, email: String, pass: String) {
        val registerUser = RegisterUserRequest(name, mob, email, pass)
        val call: Call<RegisterUserResponse> = apiService.registerNewUser(registerUser)

        call.enqueue(object : Callback<RegisterUserResponse> {
            override fun onResponse(
                call: Call<RegisterUserResponse>,
                response: Response<RegisterUserResponse>
            ) {
                if (response.isSuccessful) {
                    _registratonStatus.value = response.body()
                    _message.value = "Register success!"
                }else {
                    _message.value = "Failed to Register User"
                }
            }

            override fun onFailure(call: Call<RegisterUserResponse>, t: Throwable) {
               t.printStackTrace()
                _message.value = "An error occurred: ${t.localizedMessage}"
            }
        })
    }
}

