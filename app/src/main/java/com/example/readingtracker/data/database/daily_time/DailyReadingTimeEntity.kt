package com.example.readingtracker.data.database.daily_time

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_reading_time_table")
data class DailyReadingTimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val date:Long,
    val bookId:Int,
    val time:Int,
    val pages:Int
)
