package com.example.readingtracker.data.repository

import android.util.Log
import com.example.readingtracker.data.database.DailyReadingTimeEntity
import com.example.readingtracker.data.database.LocalDailyTimeDataSource
import com.example.readingtracker.data.mappers.getCurrentDateInLong
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DailyTimeRepository(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val localDao: LocalDailyTimeDataSource
) {
    suspend fun getTimePerDayByDate(date: Long) =
        withContext(defaultDispatcher) {
            localDao.getReadingTimePerDay(date)
        }

    init{
        CoroutineScope(defaultDispatcher).launch {
            add()
            Log.i("Add Daily Time", "Function is invoked")
        }
    }
    suspend fun add(){
        withContext(defaultDispatcher) {
            localDao.addReadingTime(
                DailyReadingTimeEntity(
                    10,
                    getCurrentDateInLong(),
                    1,
                    25,
                    12
                )
            )
        }
    }
}