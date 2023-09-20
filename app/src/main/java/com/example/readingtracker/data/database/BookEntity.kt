package com.example.readingtracker.data.database

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.readingtracker.BookFormat

@Entity(tableName = "books_table")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val author:String,
    val format:BookFormat,
    val pages:Int,
    val publishDate:Long?,
    val cover:Bitmap?
)