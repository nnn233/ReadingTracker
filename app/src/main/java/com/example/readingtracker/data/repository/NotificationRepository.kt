package com.example.readingtracker.data.repository

import com.example.readingtracker.data.data_store.LocalNotifications
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class NotificationRepository(
    defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val localNotifications: LocalNotifications
) {
    val notification: Flow<Long?>
        get() = localNotifications.notificationTime
}