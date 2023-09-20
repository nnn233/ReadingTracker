package com.example.readingtracker.domain.notification

import com.example.readingtracker.data.model.Notification
import com.example.readingtracker.data.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow

class GetNotificationUseCase(
    private val repository: NotificationRepository
) {
    operator fun invoke(): Flow<Notification> =
        repository.notification
}