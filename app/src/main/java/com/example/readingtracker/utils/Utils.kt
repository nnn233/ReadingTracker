package com.example.readingtracker.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.convertToTime(): String {
    val date= Date(this)
    val format=SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(date)
}