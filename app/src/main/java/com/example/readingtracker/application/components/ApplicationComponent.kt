package com.example.readingtracker.application.components

import com.example.readingtracker.application.factories.ViewModelFactory
import com.example.readingtracker.data.database.ReadingTrackerDatabase
import com.example.readingtracker.data.repository.BooksInProgressRepository
import com.example.readingtracker.data.repository.DailyTimeRepository
import com.example.readingtracker.data.repository.GoalsRepository
import com.example.readingtracker.data.repository.NotificationRepository

class ApplicationComponent(database: ReadingTrackerDatabase) {
    private val booksInProgressRepository =
        BooksInProgressRepository(localDao = database.progressDao)
    private val goalsRepository = GoalsRepository()
    private val notificationRepository = NotificationRepository()
    private val dailyTimeRepository = DailyTimeRepository(localDao = database.dailyReadingDao)

    val viewModelFactory = ViewModelFactory(
        booksInProgressRepository,
        goalsRepository,
        notificationRepository,
        dailyTimeRepository
    )
}