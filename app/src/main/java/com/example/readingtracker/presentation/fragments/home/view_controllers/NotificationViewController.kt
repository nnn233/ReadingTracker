package com.example.readingtracker.presentation.fragments.home.view_controllers

import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.readingtracker.R
import com.example.readingtracker.databinding.FragmentHomeBinding
import com.example.readingtracker.presentation.Status
import com.example.readingtracker.presentation.fragments.home.state_holders.HomeUIState
import com.example.readingtracker.presentation.fragments.home.state_holders.HomeViewModel
import com.example.readingtracker.utils.convertToTime
import kotlinx.coroutines.launch

class NotificationViewController(
    private val fragment: FragmentActivity,
    root: FragmentHomeBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: HomeViewModel
) {
    private var mainText: TextView = root.homeNotificationText
    private var choiceText: TextView = root.homeNotificationChoice
    fun setUpViews() {
        setUpViewModel()
        choiceText.setOnClickListener {
            // Implement TimePickerDialog
        }
    }

    private fun setUpViewModel() {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeUIState.collect { result ->
                    when (result.status) {
                        Status.SUCCESS -> result.data?.let { setUpNotifications(it) }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun setUpNotifications(item: HomeUIState) {
        if (item.isNotificationOn) {
            mainText.text =
                fragment.applicationContext.getString(R.string.notifications_turn_on)
            item.notificationTime?.let {
                choiceText.text = it.convertToTime()
            }
        } else {
            mainText.text =
                fragment.applicationContext.getString(R.string.notifications_turn_off)
            choiceText.text = fragment.applicationContext.getString(R.string.set)
        }
    }
}