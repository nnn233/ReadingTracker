package com.example.readingtracker.presentation.fragments.home.view_controllers

import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.readingtracker.databinding.FragmentHomeBinding
import com.example.readingtracker.presentation.Status
import com.example.readingtracker.presentation.fragments.home.state_holders.HomeUIState
import com.example.readingtracker.presentation.fragments.home.state_holders.HomeViewModel
import com.triggertrap.seekarc.SeekArc
import kotlinx.coroutines.launch

private const val HUNDRED_PERCENTS = 100

class GoalsViewController(
    private val fragment: FragmentActivity,
    private val binding: FragmentHomeBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: HomeViewModel
) {
    private var seekArc: SeekArc = binding.homeSeekMinutes
    private var restText: TextView = binding.homeGoalRest
    fun setUpViews() {
        setUpEditIcon()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeUIState.collect { result ->
                    when (result.status) {
                        Status.SUCCESS -> result.data?.let { setUpData(it) }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun setUpData(it: HomeUIState) {
        if (it.dailyGoal != 0) {
            seekArc.progress =
                (it.currentMinutes / it.dailyGoal.toDouble() * HUNDRED_PERCENTS).toInt()
            restText.text = if (it.dailyGoal - it.currentMinutes >= 0)
                (it.dailyGoal - it.currentMinutes).toString() else "0"
        } else {
            seekArc.progress = HUNDRED_PERCENTS
            restText.text = "0"
        }
    }

    private fun setUpEditIcon() {
        binding.homeEditGoal.setOnClickListener {
            // Implement dialog about editing goal
        }
    }
}