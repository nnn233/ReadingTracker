package com.example.readingtracker.presentation.fragments.home.view_controllers

import android.view.View
import android.view.View.OnClickListener
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.readingtracker.databinding.FragmentHomeBinding
import com.example.readingtracker.presentation.Status
import com.example.readingtracker.presentation.fragments.home.recycler_view.BooksHomeAdapter
import com.example.readingtracker.presentation.fragments.home.state_holders.BookHomeState
import com.example.readingtracker.presentation.fragments.home.state_holders.HomeUIState
import com.example.readingtracker.presentation.fragments.home.state_holders.HomeViewModel
import kotlinx.coroutines.launch

class BooksViewController(
    private val fragment: FragmentActivity,
    private val binding: FragmentHomeBinding,
    private val currentAdapter: BooksHomeAdapter,
    private val plannedAdapter: BooksHomeAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: HomeViewModel
) {

    private val addCurrent = binding.homeCurrentAdd
    private val addPlanned = binding.homePlannedAdd
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
        binding.homeRecyclerViewReadNow.apply {
            layoutManager = LinearLayoutManager(fragment)
            adapter = currentAdapter
        }
        binding.homeRecyclerViewReadingPlan.apply {
            layoutManager = LinearLayoutManager(fragment)
            adapter = plannedAdapter
        }
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