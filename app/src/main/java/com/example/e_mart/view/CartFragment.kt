package com.example.e_mart.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_mart.R
import com.example.e_mart.view.checkout.CartItemFragment
import com.example.e_mart.view.checkout.CheckoutMainActivity
import com.example.e_mart.viewmodel.SharedViewModel


class CartFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the shared ViewModel
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Find views by ID
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCart)
        val textViewTotalPrice = view.findViewById<TextView>(R.id.textViewTotalPrice)
        val checkoutButton = view.findViewById<Button>(R.id.btn_checkout)

        // Set the LayoutManager for RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe the cart items
        sharedViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            cartAdapter = CartAdapter(cartItems)
            recyclerView.adapter = cartAdapter

            // Calculate and display the total price
            val totalPrice: Double = cartItems.sumOf { it.cart_price }
            textViewTotalPrice.text = "Total: $${totalPrice}"
        }

        // Set the Checkout button's click listener
        checkoutButton.setOnClickListener {
            navigateToCheckout()
        }
    }

    // Function to navigate to CheckoutMainActivity
    private fun navigateToCheckout() {
        val intent = Intent(requireActivity(), CheckoutMainActivity::class.java)
        startActivity(intent)
    }
}
