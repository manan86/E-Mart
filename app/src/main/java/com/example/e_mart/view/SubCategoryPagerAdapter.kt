package com.example.e_mart.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_mart.R
import com.example.e_mart.model.Subcategory

class SubCategoryPagerAdapter(
    private val context: Context,
    private val subcategories: List<Subcategory>
) : RecyclerView.Adapter<SubCategoryPagerAdapter.SubCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.subcategory_item_view, parent, false)
        return SubCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        val subcategory = subcategories[position]
        holder.subcategoryName.text = subcategory.subcategory_name
    }

    override fun getItemCount(): Int = subcategories.size

    inner class SubCategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subcategoryName: TextView = view.findViewById(R.id.subcategory_name_text)
    }
}

