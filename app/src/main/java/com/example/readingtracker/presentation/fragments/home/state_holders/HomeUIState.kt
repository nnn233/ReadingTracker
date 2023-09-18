package com.example.readingtracker.presentation.fragments.home.state_holders

data class HomeUIState(
    var isNotificationOn: Boolean = false,
    var notificationTime: Long?,
    var currentBooks: List<BookHomeState>,
    var plannedBooks: List<BookHomeState>,
    var dailyGoal: Int = 0,
    var currentMinutes: Int = 0
)
