package com.example.readingtracker.domain.notification

import com.example.readingtracker.data.model.Notification
import com.example.readingtracker.data.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotificationUseCase(
    private val repository: NotificationRepository
) {
    operator fun invoke(): Flow<Notification> =
        repository.notification.map {
            if (it != null) Notification(true, it)
            else Notification(false, 0L)
        }
}