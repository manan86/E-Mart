package com.example.e_mart.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_mart.R
import com.example.e_mart.model.AddCartProduct
import com.example.e_mart.model.Product
import com.example.e_mart.viewmodel.SharedViewModel

class ItemAdapter(private val context: Context, private var list: List<Product>, private val sharedViewModel: SharedViewModel) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    val baseUrl = "https://apolisrises.co.in/myshop/images/"

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.image_list_view)
        val itemName: TextView = view.findViewById(R.id.item_name)
        val itemDescription: TextView = view.findViewById(R.id.item_desc)
        val itemPrice: TextView = view.findViewById(R.id.item_price)
        val addToCartButton: Button = view.findViewById(R.id.btn_add_to_cart)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingbar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_list__viewitem_subcategory, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        val fullImage = baseUrl + item.product_image_url

        // Bind the product data to the views
        holder.itemName.text = item.product_name
        holder.itemDescription.text = item.description
        holder.itemPrice.text = "$${item.price}"

        // Load the product image using Glide
        Glide.with(context)
            .load(fullImage)
            .into(holder.itemImage)

        // Set the rating bar
        holder.ratingBar.rating = item.average_rating.toFloat()

        holder.addToCartButton.setOnClickListener {
            val addCartProduct = AddCartProduct(
                cart_id = item.product_id.toInt(), // Assuming item has a product_id property
                cart_name = item.product_name,
                cart_price = item.price.toDouble()
            )
            sharedViewModel.addToCart(addCartProduct)

            Toast.makeText(context, "${item.product_name} added to cart", Toast.LENGTH_SHORT).show()
            navigateToCartFragment()

        }
    }

    private fun navigateToCartFragment() {
        val activity = context as AppCompatActivity
        val cartFragment = CartFragment() // Replace with your actual CartFragment
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container1, cartFragment) // Replace with the ID of your container
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun getItemCount() = list.size

    fun updateItems(newItems: List<Product>) {
        this.list = newItems
        notifyDataSetChanged()
    }
}

/*
class ItemAdapter(
    private val context: Context,
    private var list: List<Product>,
    private val sharedViewModel: SharedViewModel
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    val baseUrl = "https://apolisrises.co.in/myshop/images/"

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.image_list_view)
        val itemName: TextView = view.findViewById(R.id.item_name)
        val itemDescription: TextView = view.findViewById(R.id.item_desc)
        val itemPrice: TextView = view.findViewById(R.id.item_price)
        val addToCartButton: Button = view.findViewById(R.id.btn_add_to_cart)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingbar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_list__viewitem_subcategory, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        val fullImage = baseUrl + item.product_image_url

        // Bind the product data to the views
        holder.itemName.text = item.product_name
        holder.itemDescription.text = item.description
        holder.itemPrice.text = "$${item.price}"

        // Load the product image using Glide
        Glide.with(context)
            .load(fullImage)
            .into(holder.itemImage)

        // Set the rating bar
        holder.ratingBar.rating = item.average_rating.toFloat()

        holder.addToCartButton.setOnClickListener {
            val addCartProduct = AddCartProduct(
                id = item.product_id, // Assuming item has a product_id property
                name = item.product_name,
                price = item.price
            )
            sharedViewModel.addToCart(addCartProduct) // Add product to cart

            Toast.makeText(context, "${item.product_name} added to cart", Toast.LENGTH_SHORT).show()
            navigateToCartFragment()
        }
    }

    private fun navigateToCartFragment() {
        val activity = context as AppCompatActivity
        val cartFragment = CartFragment()
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container1, cartFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun getItemCount() = list.size

    fun updateItems(newItems: List<Product>) {
        this.list = newItems
        notifyDataSetChanged()
    }
}

 */