package com.example.readingtracker.data.repository

import com.example.readingtracker.data.database.daily_time.LocalDailyTimeDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DailyTimeRepository(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val localDao: LocalDailyTimeDataSource
) {
    suspend fun getTimePerDayByDate(date: Long) : Flow<Int?> =
        withContext(defaultDispatcher) {
            localDao.getReadingTimePerDay(date)
        }
}