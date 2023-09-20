package com.example.readingtracker.data.database.review

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "reviews_table"
)
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val review: String,
    val grade: Int
)
