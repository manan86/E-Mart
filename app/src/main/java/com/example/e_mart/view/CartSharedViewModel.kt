package com.example.e_mart.view
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_mart.model.AddCartProduct

class CartSharedViewModel : ViewModel() {
    private val _selectedProduct = MutableLiveData<AddCartProduct>()
    val selectedProduct: LiveData<AddCartProduct> get() = _selectedProduct

    fun selectProduct(product: AddCartProduct) {
        _selectedProduct.value = product
    }
}
