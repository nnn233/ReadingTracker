package com.example.readingtracker.presentation.fragments.home.view_controllers

import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
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
    root: View,
    private val currentAdapter: BooksHomeAdapter,
    private val plannedAdapter: BooksHomeAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: HomeViewModel
) {
    private val recyclerViewCurrentBooks: RecyclerView =
        root.findViewById(R.id.home_recycler_view_read_now)
    private val recyclerViewPlannedBooks: RecyclerView =
        root.findViewById(R.id.home_recycler_view_reading_plan)
    private val addCurrent: TextView = root.findViewById(R.id.home_current_add)
    private val addPlanned: TextView = root.findViewById(R.id.home_planned_add)

    fun setUpViews() {
        setUpRecyclerViews()
        setUpAddButtons()
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
        if (data.currentBooks.isNotEmpty()) {
            currentAdapter.items = data.currentBooks as MutableList<BookHomeState>
            addCurrent.visibility = View.GONE
        } else addCurrent.visibility = View.VISIBLE

        if (data.plannedBooks.isNotEmpty()) {
            plannedAdapter.items = data.plannedBooks as MutableList<BookHomeState>
            addPlanned.visibility = View.GONE
        } else addPlanned.visibility = View.VISIBLE
    }

    private fun setUpRecyclerViews() {
        recyclerViewCurrentBooks.layoutManager = LinearLayoutManager(fragment)
        recyclerViewCurrentBooks.adapter = currentAdapter

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

    private val onClickListenerForButton = OnClickListener {
        //add book to list
    }

    private fun setUpAddButtons() {
        addCurrent.setOnClickListener(onClickListenerForButton)
        addPlanned.setOnClickListener(onClickListenerForButton)
    }
}