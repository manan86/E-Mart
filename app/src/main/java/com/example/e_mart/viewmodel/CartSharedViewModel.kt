package com.example.e_mart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_mart.model.AddCartProduct

//when click add to cart button add it cart fragment
class SharedViewModel : ViewModel() {
    // MutableLiveData to hold the list of cart items
    private val _cartItems = MutableLiveData<MutableList<AddCartProduct>>(mutableListOf())
    val cartItems: LiveData<MutableList<AddCartProduct>> = _cartItems

    // Function to add a product to the cart
    fun addToCart(product: AddCartProduct) {
        val currentCartItems = _cartItems.value ?: mutableListOf()
        currentCartItems.add(product)
        _cartItems.value = currentCartItems
    }

    //adding to keep cart item in checkout page

    fun clearCart() {
        _cartItems.value = mutableListOf()
    }
}