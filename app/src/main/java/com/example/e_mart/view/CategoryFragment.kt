package com.example.e_mart.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_mart.R
import com.example.e_mart.databinding.FragmentCategoryBinding
import com.example.e_mart.viewmodel.CategoryViewModel


class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var viewModel: CategoryViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_menu_24)
            title = "Super Cart"
        }

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            when (menuItem.itemId) {
                R.id.home -> requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container1,CategoryFragment()).commit()
                R.id.cart -> requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container1,CartFragment()).commit()
                R.id.order -> showToast("Order")
                R.id.profile -> showToast("Profile")
                R.id.logout -> {showToast("Logging Out...")
                    requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container1,LoginFragment()).commit()
            }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        categoryAdapter = CategoryAdapter(requireContext(), emptyList())

        binding.rvDashboard.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = categoryAdapter
        }
        observeViewModel()
        viewModel.getCategory()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun observeViewModel() {
        viewModel.categoryView.observe(viewLifecycleOwner) { categories ->
            if (categories != null) {
                categoryAdapter = CategoryAdapter(requireContext(), categories)
                binding.rvDashboard.adapter = categoryAdapter
            }
        }

        // Observe error messages
        viewModel.message.observe(viewLifecycleOwner) { msg ->
            if (msg != null) {
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
