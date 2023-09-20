package com.example.readingtracker.data.database.book_progress

import androidx.room.Embedded
import androidx.room.Relation
import com.example.readingtracker.data.database.book.BookEntity
import com.example.readingtracker.data.database.progress.ProgressEntity

data class BookWithProgress(
    @Embedded
    val book: BookEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "bookId"
    )
    val progresses: List<ProgressEntity> = listOf()
)