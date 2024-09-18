package com.example.e_mart.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_mart.R
import com.example.e_mart.model.Category


class CategoryAdapter(private val context: Context,private val list: List<Category>)
    :RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    val baseUrl = "https://apolisrises.co.in/myshop/images/"

        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
           val adapter_name : TextView = view.findViewById(R.id.category_name)
            val adapter_image : ImageView = view.findViewById(R.id.category_image)

            init {
                view.setOnClickListener {
                    val category = list[adapterPosition]
                    val fragment = SubCategoryFragment().apply {
                        arguments = Bundle().apply {
                            putInt("category_id", category.id)
                        }
                    }

                    (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container1, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categortItem = list[position]
        val fullImage = baseUrl + categortItem.image

        Glide.with(holder.itemView.context)
            .load(fullImage)
            .into(holder.adapter_image)

        holder.adapter_name.text = categortItem.name
    }
}
