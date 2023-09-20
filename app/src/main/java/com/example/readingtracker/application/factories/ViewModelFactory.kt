package com.example.readingtracker.application.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.readingtracker.data.repository.BooksInProgressRepository
import com.example.readingtracker.data.repository.DailyTimeRepository
import com.example.readingtracker.data.repository.GoalsRepository
import com.example.readingtracker.data.repository.NotificationRepository
import com.example.readingtracker.presentation.fragments.home.state_holders.HomeViewModel

class ViewModelFactory(
    private val booksInProgressRepository: BooksInProgressRepository,
    private val goalsRepository: GoalsRepository,
    private val notificationRepository: NotificationRepository,
    private val dailyTimeRepository: DailyTimeRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        HomeViewModel::class.java -> HomeViewModel(
            booksInProgressRepository,
            notificationRepository,
            goalsRepository,
            dailyTimeRepository
        )

        else -> throw IllegalArgumentException("${modelClass.simpleName} cannot be provided.")
    } as T
}