package com.example.readingtracker.application.components

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.readingtracker.application.factories.ViewModelFactory
import com.example.readingtracker.data.data_store.LocalGoals
import com.example.readingtracker.data.data_store.LocalNotifications
import com.example.readingtracker.data.database.ReadingTrackerDatabase
import com.example.readingtracker.data.repository.BooksInProgressRepository
import com.example.readingtracker.data.repository.DailyTimeRepository
import com.example.readingtracker.data.repository.GoalsRepository
import com.example.readingtracker.data.repository.NotificationRepository
import com.example.readingtracker.domain.books_with_progresses.GetCurrentBooksWithProgressesUseCase
import com.example.readingtracker.domain.books_with_progresses.GetPlannedBooksWithProgressesUseCase
import com.example.readingtracker.domain.daily_time.GetDailyReadingTimeByDateUseCase
import com.example.readingtracker.domain.goals.GetGoalUseCase
import com.example.readingtracker.domain.notification.GetNotificationUseCase

class ApplicationComponent(
    database: ReadingTrackerDatabase,
    dataStore: DataStore<Preferences>
) {
    private val localNotifications = LocalNotifications(dataStore)
    private val localGoals = LocalGoals(dataStore)

    private val booksInProgressRepository =
        BooksInProgressRepository(localDao = database.progressDao)
    private val goalsRepository = GoalsRepository(localGoals = localGoals)
    private val notificationRepository =
        NotificationRepository(localNotifications = localNotifications)
    private val dailyTimeRepository = DailyTimeRepository(localDao = database.dailyReadingDao)

    private val getCurrentBooksWithProgressesUseCase =
        GetCurrentBooksWithProgressesUseCase(booksInProgressRepository)
    private val getPlannedBooksWithProgressesUseCase =
        GetPlannedBooksWithProgressesUseCase(booksInProgressRepository)
    private val getGoalUseCase = GetGoalUseCase(goalsRepository)
    private val getNotificationUseCase = GetNotificationUseCase(notificationRepository)
    private val getDailyReadingTimeByDateUseCase =
        GetDailyReadingTimeByDateUseCase(dailyTimeRepository)

    val viewModelFactory = ViewModelFactory(
        getCurrentBooksWithProgressesUseCase,
        getPlannedBooksWithProgressesUseCase,
        getNotificationUseCase,
        getDailyReadingTimeByDateUseCase,
        getGoalUseCase
    )
}