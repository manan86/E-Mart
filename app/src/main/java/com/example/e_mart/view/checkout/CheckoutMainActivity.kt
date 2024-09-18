package com.example.e_mart.view.checkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.e_mart.databinding.ActivityCheckoutMainBinding
import com.example.e_mart.viewmodel.SharedViewModel
import com.google.android.material.tabs.TabLayoutMediator

class CheckoutMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutMainBinding
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckoutMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)


        initViews()
    }

    private fun initViews() {
        val fragments = listOf(
            CartItemFragment(),
            DeliveyFragment(),
            PaymentFragment(),
            SummaryFragment()
        )

        val adapterCart = checkoutViewPagerAdapter( fragments,this)
        binding.viewPagerCart.adapter = adapterCart

        TabLayoutMediator(binding.tabLayoutCart, binding.viewPagerCart) { tab, position ->
            when (position) {
                0 -> tab.text = "Cart Item"
                1 -> tab.text = "Delivery"
                2 -> tab.text = "Payment"
                3 -> tab.text = "Summary"
            }
        }.attach()
    }
}