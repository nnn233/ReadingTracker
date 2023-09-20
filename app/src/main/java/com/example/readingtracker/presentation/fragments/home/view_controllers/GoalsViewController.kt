package com.example.readingtracker.presentation.fragments.home.view_controllers

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.readingtracker.R
import com.example.readingtracker.presentation.fragments.home.state_holders.HomeViewModel
import com.triggertrap.seekarc.SeekArc
import kotlinx.coroutines.launch

private const val HUNDRED_PERCENTS = 100

class GoalsViewController(
    private val fragment: FragmentActivity,
    root: View,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: HomeViewModel
) {

    private var editIcon: ImageView = root.findViewById(R.id.home_edit_goal)
    private var seekArc: SeekArc = root.findViewById(R.id.home_seek_minutes)
    private var restText: TextView = root.findViewById(R.id.home_goal_rest)
    fun setUpViews() {
        setUpEditIcon()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeUIState.collect {
                    Log.i("DailyGoal", "${it.currentMinutes} from ${it.dailyGoal}")
                    if(it.dailyGoal!=0) {
                        seekArc.progress =
                            (it.currentMinutes / it.dailyGoal.toDouble() * HUNDRED_PERCENTS).toInt()
                        restText.text = (it.dailyGoal - it.currentMinutes).toString()
                    }else{
                        seekArc.progress = HUNDRED_PERCENTS
                        restText.text = "0"
                    }
                }
            }
        }
    }

    private fun setUpEditIcon() {
        editIcon.setOnClickListener {
            // Implement dialog about editing goal
        }
    }
}