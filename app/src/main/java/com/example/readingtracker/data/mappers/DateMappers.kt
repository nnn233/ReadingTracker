package com.example.readingtracker.data.mappers

import java.util.Calendar
import java.util.GregorianCalendar

fun getCurrentDateInLong():Long{
    val calendar = Calendar.getInstance()
    val current = GregorianCalendar(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    return current.timeInMillis
}