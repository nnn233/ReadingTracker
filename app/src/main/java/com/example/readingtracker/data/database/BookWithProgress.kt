package com.example.readingtracker.data.database

import androidx.room.Embedded
import androidx.room.Relation

data class BookWithProgress(
    @Embedded
    val book: BookEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "bookId"
    )
    val progresses: List<ProgressEntity> = listOf()
)