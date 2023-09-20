package com.example.readingtracker.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.readingtracker.presentation.fragments.home.state_holders.BookStatus

@Entity(tableName = "progress_table")
data class ProgressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bookId: Int,
    var currentPage: Int?,
    val startDate: Long?,
    var finishDate: Long?,
    var notes: String = "",
    var reviewId: Int?,
    var status: BookStatus = if (finishDate != null)
        BookStatus.READ_ALREADY
    else if (currentPage != null || startDate != null)
        BookStatus.READ
    else BookStatus.GENERAL
)