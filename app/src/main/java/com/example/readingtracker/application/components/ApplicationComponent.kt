package com.example.readingtracker.application.components

import com.example.readingtracker.application.factories.ViewModelFactory
import com.example.readingtracker.data.repository.BooksInProgressRepository
import com.example.readingtracker.data.repository.GoalsRepository
import com.example.readingtracker.data.repository.NotificationRepository

class ApplicationComponent {
    private val booksInProgressRepository =
        BooksInProgressRepository()
    private val goalsRepository=GoalsRepository()
    private val notificationRepository=NotificationRepository()

    val viewModelFactory = ViewModelFactory(booksInProgressRepository, goalsRepository, notificationRepository)
}