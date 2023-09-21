package com.example.readingtracker.presentation.fragments.home.view_controllers

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.readingtracker.R
import com.example.readingtracker.presentation.Status
import com.example.readingtracker.presentation.fragments.home.recycler_view.BooksHomeAdapter
import com.example.readingtracker.presentation.fragments.home.state_holders.BookHomeState
import com.example.readingtracker.presentation.fragments.home.state_holders.HomeUIState
import com.example.readingtracker.presentation.fragments.home.state_holders.HomeViewModel
import kotlinx.coroutines.launch

class BooksViewController(
    private val fragment: FragmentActivity,
    private val root: View,
    private val currentAdapter: BooksHomeAdapter,
    private val plannedAdapter: BooksHomeAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: HomeViewModel
) {
    private lateinit var recyclerViewCurrentBooks: RecyclerView
    private lateinit var recyclerViewPlannedBooks: RecyclerView
    fun setUpViews() {
        setUpRecyclerViews()
        setUpAdapterClickListener()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeUIState.collect { result ->
                    when (result.status) {
                        Status.SUCCESS -> result.data?.let { setUpBooks(it) }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun setUpBooks(data: HomeUIState) {
        if (data.currentBooks.isNotEmpty())
            currentAdapter.items = data.currentBooks as MutableList<BookHomeState>
        if (data.plannedBooks.isNotEmpty())
            plannedAdapter.items = data.plannedBooks as MutableList<BookHomeState>
    }

    private fun setUpRecyclerViews() {
        recyclerViewCurrentBooks = root.findViewById(R.id.home_recycler_view_read_now)
        recyclerViewCurrentBooks.layoutManager = LinearLayoutManager(fragment)
        recyclerViewCurrentBooks.adapter = currentAdapter

        recyclerViewPlannedBooks = root.findViewById(R.id.home_recycler_view_reading_plan)
        recyclerViewPlannedBooks.layoutManager = LinearLayoutManager(fragment)
        recyclerViewPlannedBooks.adapter = plannedAdapter
    }

    private fun setUpAdapterClickListener() {
        currentAdapter.setOnClickListeners(onClickListener, onLongClickListener)
        plannedAdapter.setOnClickListeners(onClickListener, onLongClickListener)
    }

    private val onClickListener = object :
        BooksHomeAdapter.OnClickListener {
        override fun onClick(position: Int, id: Int) {
            //to another screen
        }
    }

    private val onLongClickListener = object : BooksHomeAdapter.OnLongClickListener {
        override fun onLongClick(position: Int, id: Int) {
            //show popup menu
        }
    }
}