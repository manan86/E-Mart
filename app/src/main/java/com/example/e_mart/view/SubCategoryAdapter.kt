package com.example.e_mart.view

import com.example.e_mart.model.Subcategory
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_mart.R

class SubCategoryAdapter(
    private val context: Context,
    private val subcategories: List<Subcategory>
) : RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.subcategory_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = subcategories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subcategoryItem = subcategories[position]
      //  val fullImage = "https://apolisrises.co.in/myshop/images/" + subcategoryItem.subcategory_image_url

        // Load subcategory image using Glide
//        Glide.with(holder.itemView.context)
//            .load(fullImage)
//            .into(holder.subcategoryImage)

        // Set the subcategory name
        holder.subcategoryName.text = subcategoryItem.subcategory_name
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subcategoryName: TextView = view.findViewById(R.id.subcategory_name_text)
       // val subcategoryImage: ImageView = view.findViewById(R.id.subcategory_image)
    }
}


