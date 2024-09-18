package com.example.e_mart.view.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_mart.R
import com.example.e_mart.databinding.FragmentCartItemBinding
import com.example.e_mart.view.CartAdapter
import com.example.e_mart.viewmodel.SharedViewModel


class CartItemFragment : Fragment() {

    private lateinit var binding : FragmentCartItemBinding
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the shared ViewModel
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Find views by ID
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCartItem)
        val textViewTotalPrice = view.findViewById<TextView>(R.id.textViewTotalCartPrice)

        // Set the LayoutManager for RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe the cart items
        sharedViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            cartAdapter = CartAdapter(cartItems)
            recyclerView.adapter = cartAdapter

            // Display total price
            val totalPrice: Double = cartItems.sumOf { it.cart_price }
            textViewTotalPrice.text = "Total: $${totalPrice}"
        }

        binding.btnNexttoDelivery.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container2,DeliveyFragment())
                .commit()
        }
    }
}


