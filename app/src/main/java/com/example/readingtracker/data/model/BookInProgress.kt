package com.example.readingtracker.data.model

import android.graphics.Bitmap
import com.example.readingtracker.presentation.fragments.home.state_holders.BookStatus

data class BookInProgress(
    val id: Int = 0,
    val title: String = "",
    val author: String = "",
    val currentPage: Int = 0,
    val pages: Int = 0,
    val status: BookStatus = BookStatus.GENERAL,
    val cover: Bitmap?
)
