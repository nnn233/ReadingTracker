package com.example.readingtracker.presentation.fragments.home.state_holders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readingtracker.data.mappers.getCurrentDateInLong
import com.example.readingtracker.data.mappers.toBookHomeState
import com.example.readingtracker.data.repository.BooksInProgressRepository
import com.example.readingtracker.data.repository.DailyTimeRepository
import com.example.readingtracker.data.repository.GoalsRepository
import com.example.readingtracker.data.repository.NotificationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val booksInProgressRepository: BooksInProgressRepository,
    private val notificationRepository: NotificationRepository,
    private val goalsRepository: GoalsRepository,
    private val dailyTimeRepository: DailyTimeRepository
) :
    ViewModel() {
    private val _homeUIState = MutableStateFlow(
        HomeUIState(
            notificationTime = null,
            currentBooks = listOf(),
            plannedBooks = listOf()
        )
    )
    val homeUIState: StateFlow<HomeUIState>
        get() = _homeUIState

    init {
        viewModelScope.launch {
            booksInProgressRepository.booksInProgress
                .collect { item ->
                    _homeUIState.value = _homeUIState.value.copy(
                        notificationTime = null,
                        currentBooks = item.filter { it.status == BookStatus.READ }
                            .map { it.toBookHomeState() },
                        plannedBooks = item.filter { it.status == BookStatus.PLANNED_READ }
                            .map { it.toBookHomeState() })
                }
        }
        viewModelScope.launch {
            notificationRepository.notification
                .collect {
                    _homeUIState.value = _homeUIState.value.copy(
                        isNotificationOn = it.state,
                        notificationTime = it.time
                    )
                }
        }
        viewModelScope.launch {
            goalsRepository.goals.collect {
                _homeUIState.value = _homeUIState.value.copy(
                    dailyGoal = it.dailyGoal ?: 0
                )
            }
        }
        viewModelScope.launch {
            dailyTimeRepository.getTimePerDayByDate(getCurrentDateInLong()).collect {
                _homeUIState.value = _homeUIState.value.copy(
                    currentMinutes = it
                )
                Log.i("DailyTime", "$it")
            }
        }
    }
}