package com.example.readingtracker.application.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.readingtracker.domain.books_with_progresses.GetCurrentBooksWithProgressesUseCase
import com.example.readingtracker.domain.books_with_progresses.GetPlannedBooksWithProgressesUseCase
import com.example.readingtracker.domain.daily_time.GetDailyReadingTimeByDateUseCase
import com.example.readingtracker.domain.goals.GetGoalUseCase
import com.example.readingtracker.domain.notification.GetNotificationUseCase
import com.example.readingtracker.presentation.fragments.home.state_holders.HomeViewModel

class ViewModelFactory(
    private val getCurrentBooksWithProgressesUseCase: GetCurrentBooksWithProgressesUseCase,
    private val getPlannedBooksWithProgressesUseCase: GetPlannedBooksWithProgressesUseCase,
    private val getNotificationUseCase: GetNotificationUseCase,
    private val getDailyReadingTimeByDateUseCase: GetDailyReadingTimeByDateUseCase,
    private val getGoalUseCase: GetGoalUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        HomeViewModel::class.java -> HomeViewModel(
            getCurrentBooksWithProgressesUseCase,
            getPlannedBooksWithProgressesUseCase,
            getNotificationUseCase,
            getGoalUseCase,
            getDailyReadingTimeByDateUseCase
        )

        else -> throw IllegalArgumentException("${modelClass.simpleName} cannot be provided.")
    } as T
}