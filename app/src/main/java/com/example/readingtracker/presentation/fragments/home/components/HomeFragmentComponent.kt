package com.example.readingtracker.presentation.fragments.home.components

import androidx.fragment.app.FragmentActivity
import com.example.readingtracker.presentation.fragments.home.recycler_view.BooksHomeAdapter
import com.example.readingtracker.presentation.fragments.home.state_holders.HomeViewModel

class HomeFragmentComponent(
    val fragment: FragmentActivity,
    val viewModel: HomeViewModel
) {
    val currentBooksAdapter = BooksHomeAdapter(fragment)
    val plannedBooksAdapter = BooksHomeAdapter(fragment)
}