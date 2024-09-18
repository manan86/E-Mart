package com.example.e_mart.view.checkout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class checkoutViewPagerAdapter(
    private val fragments: List<Fragment>,
    fragmentManager: FragmentActivity
) : FragmentStateAdapter(fragmentManager) {
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}