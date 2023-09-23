package com.example.readingtracker.presentation.fragments.home.view_controllers

import com.example.readingtracker.databinding.FragmentHomeBinding

class AppBarViewController(
    private val binding: FragmentHomeBinding
) {
    fun setUpViews() {
        binding.homeNestedScrollView.setOnScrollChangeListener { v, _, _, _, _ ->
            binding.homeActionBar.topNavigationBar.elevation =
                if (v.canScrollVertically(SCROLL_DIRECTION_UP)) 4f else 0f
        }
    }

    companion object {
        private const val SCROLL_DIRECTION_UP = -1
    }
}