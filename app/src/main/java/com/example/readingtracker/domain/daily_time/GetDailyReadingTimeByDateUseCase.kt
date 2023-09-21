package com.example.readingtracker.domain.daily_time

import com.example.readingtracker.data.mappers.getCurrentDateInLong
import com.example.readingtracker.data.repository.DailyTimeRepository
import kotlinx.coroutines.flow.Flow

class GetDailyReadingTimeByDateUseCase(
    private val repository: DailyTimeRepository
) {
    suspend operator fun invoke(): Flow<Int?> =
        repository.getTimePerDayByDate(getCurrentDateInLong())
}