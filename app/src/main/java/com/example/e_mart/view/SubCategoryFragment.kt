package com.example.e_mart.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.e_mart.databinding.FragmentSubCategoryBinding
import com.example.e_mart.model.ApiClient
import com.example.e_mart.model.ApiService
import com.example.e_mart.model.ItemsResponse
import com.example.e_mart.model.SubCategoryResponse
import com.example.e_mart.viewmodel.SharedViewModel
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubCategoryFragment : Fragment() {

    private lateinit var binding: FragmentSubCategoryBinding
    private val apiService = ApiClient.retrofit.create(ApiService::class.java)
    private lateinit var viewPagerAdapter: SubCategoryPagerAdapter
    private lateinit var itemsAdapter: ItemAdapter
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val categoryId = arguments?.getInt("category_id") ?: return

        // Initialize the RecyclerView adapter for items
        itemsAdapter = ItemAdapter(requireContext(), listOf(),sharedViewModel)

        // **Set the LayoutManager for the RecyclerView**
        binding.itemsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.itemsRecyclerView.adapter = itemsAdapter

        // Fetch subcategories
        fetchSubcategories(categoryId)
    }

    private fun fetchSubcategories(categoryId: Int) {
        val call = apiService.getSubcategory(categoryId)

        call.enqueue(object : Callback<SubCategoryResponse> {
            override fun onResponse(
                call: Call<SubCategoryResponse>,
                response: Response<SubCategoryResponse>
            ) {
                if (!response.isSuccessful) {
                    Toast.makeText(requireContext(), "Failed to fetch subcategories", Toast.LENGTH_SHORT).show()
                    return
                }

                val result = response.body()
                result?.let {
                    if (it.subcategories.isNotEmpty()) {
                        // Initialize the ViewPager adapter with fetched subcategories
                        viewPagerAdapter = SubCategoryPagerAdapter(requireContext(), it.subcategories)
                        binding.viewPager.adapter = viewPagerAdapter

                        // Attach TabLayout with ViewPager2
                        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                            tab.text = it.subcategories[position].subcategory_name
                        }.attach()

                        // Fetch items for the first subcategory by default
                        fetchItemsForSubcategory(it.subcategories[0].subcategory_id.toInt())

                        // Listen for page changes on ViewPager
                        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                            override fun onPageSelected(position: Int) {
                                super.onPageSelected(position)
                                // Fetch items for the selected subcategory
                                fetchItemsForSubcategory(it.subcategories[position].subcategory_id.toInt())
                            }
                        })
                    } else {
                        Toast.makeText(requireContext(), "No subcategories available", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<SubCategoryResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchItemsForSubcategory(subcategoryId: Int) {
        val call = apiService.getItemsForSubcategory(subcategoryId)
        call.enqueue(object : Callback<ItemsResponse> {
            override fun onResponse(call: Call<ItemsResponse>, response: Response<ItemsResponse>) {
                if (response.isSuccessful) {
                    val items = response.body()?.products ?: listOf()

                    // Log the response for debugging
                    Log.d("ItemsResponse", "Response for subcategory $subcategoryId: ${response.body()}")

                    if (items.isNotEmpty()) {
                        itemsAdapter.updateItems(items)
                    } else {
                        Toast.makeText(requireContext(), "No products available", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to fetch items", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ItemsResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
