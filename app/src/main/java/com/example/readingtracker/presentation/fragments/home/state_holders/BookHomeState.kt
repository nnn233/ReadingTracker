package com.example.readingtracker.presentation.fragments.home.state_holders

import android.graphics.Bitmap

data class BookHomeState(
    val id:Int,
    val title:String,
    val author:String,
    val currentPage:Int,
    val pages:Int,
    val status:BookStatus,
    val cover: Bitmap?
)
