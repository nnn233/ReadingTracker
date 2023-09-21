package com.example.readingtracker.data.database

import androidx.room.Embedded
import androidx.room.Relation
import com.example.readingtracker.data.database.book.BookEntity
import com.example.readingtracker.data.database.daily_time.DailyReadingTimeEntity

data class BookWithDailyTime(
    @Embedded
    val book: BookEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "bookId"
    )
    val dailyReadingTime: DailyReadingTimeEntity
)
