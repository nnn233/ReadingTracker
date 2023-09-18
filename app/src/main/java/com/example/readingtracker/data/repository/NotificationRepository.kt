package com.example.readingtracker.data.repository

import com.example.readingtracker.data.model.Notification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class NotificationRepository(private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default) {
    private val _notification : MutableStateFlow<Notification> = MutableStateFlow(Notification(false, 0L))
    val notification: StateFlow<Notification>
        get() = _notification
}