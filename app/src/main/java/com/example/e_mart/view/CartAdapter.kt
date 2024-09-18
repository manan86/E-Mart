package com.example.e_mart.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_mart.R
import com.example.e_mart.model.AddCartProduct

//To display add to cart item in recycler Viwew thats why making this adapter
class CartAdapter (private val cartItem : List<AddCartProduct> ):
    RecyclerView.Adapter<CartAdapter.CartViewHolder>(){

        inner class CartViewHolder(view : View):RecyclerView.ViewHolder(view){
            val productName : TextView = view.findViewById(R.id.tv_add_cart_name)
            val productPrice : TextView = view.findViewById(R.id.tv_add_cart_price)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.addcart_item_view,parent,false)
        return CartViewHolder(view)
    }

    override fun getItemCount() = cartItem.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItem[position]
        holder.productName.text = item.cart_name
        holder.productPrice.text = "$${item.cart_price}"
    }
}
