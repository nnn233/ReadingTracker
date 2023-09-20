package com.example.readingtracker.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDailyTimeDataSource {
    @Query("SELECT SUM(time) FROM daily_reading_time_table WHERE date=:date")
    fun getReadingTimePerDay(date:Long): Flow<Int>

    @Query("SELECT * FROM daily_reading_time_table")
    fun getTimes(): Flow<List<DailyReadingTimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReadingTime(item:DailyReadingTimeEntity)
}