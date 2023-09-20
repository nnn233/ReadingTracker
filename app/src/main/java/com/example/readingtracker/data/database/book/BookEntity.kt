package com.example.readingtracker.data.database.book

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.readingtracker.utils.BookFormat

@Entity(tableName = "books_table")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val author:String,
    val format: BookFormat,
    val pages:Int,
    val publishDate:Long?,
    val cover:Bitmap?
)