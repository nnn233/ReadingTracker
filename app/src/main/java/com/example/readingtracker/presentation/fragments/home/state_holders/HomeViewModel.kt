package com.example.readingtracker.presentation.fragments.home.state_holders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readingtracker.domain.books_with_progresses.GetCurrentBooksWithProgressesUseCase
import com.example.readingtracker.domain.books_with_progresses.GetPlannedBooksWithProgressesUseCase
import com.example.readingtracker.domain.daily_time.GetDailyReadingTimeByDateUseCase
import com.example.readingtracker.domain.goals.GetGoalUseCase
import com.example.readingtracker.domain.notification.GetNotificationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val currentBookUseCase: GetCurrentBooksWithProgressesUseCase,
    private val plannedBookUseCase: GetPlannedBooksWithProgressesUseCase,
    private val notificationUseCase: GetNotificationUseCase,
    private val goalUseCase: GetGoalUseCase,
    private val dailyTimeUseCase: GetDailyReadingTimeByDateUseCase
) : ViewModel() {

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
            currentBookUseCase()
                .collect { list ->
                    _homeUIState.value = _homeUIState.value.copy(
                        currentBooks = list
                    )
                }
        }

        viewModelScope.launch {
            plannedBookUseCase()
                .collect { list ->
                    _homeUIState.value = _homeUIState.value.copy(
                        plannedBooks = list
                    )
                }
        }
        viewModelScope.launch {
            notificationUseCase()
                .collect {
                    _homeUIState.value = _homeUIState.value.copy(
                        isNotificationOn = it.state,
                        notificationTime = it.time
                    )
                }
        }
        viewModelScope.launch {
            goalUseCase().collect {
                _homeUIState.value = _homeUIState.value.copy(
                    dailyGoal = it.dailyGoal ?: 0
                )
            }
        }
        viewModelScope.launch {
            dailyTimeUseCase().collect {
                _homeUIState.value = _homeUIState.value.copy(
                    currentMinutes = it
                )
            }
        }
    }
}