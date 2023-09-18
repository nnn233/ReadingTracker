package com.example.readingtracker.data.repository

import com.example.readingtracker.data.model.BookInProgress
import com.example.readingtracker.presentation.fragments.home.state_holders.BookStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BooksInProgressRepository(private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default) {
    private val _booksInProgress: MutableStateFlow<List<BookInProgress>> = MutableStateFlow(
        listOf(
            BookInProgress(
                1,
                "Гордость и предубеждение",
                "Джейн Остин",
                12,
                250,
                BookStatus.READ,
                null
            ),
            BookInProgress(
                2,
                "Богиня Глюкозы",
                "Девушка",
                5,
                7000,
                BookStatus.READ,
                null
            ),
            BookInProgress(
                3,
                "Жутко громко",
                "Джонатан Фойер",
                450,
                725,
                BookStatus.READ,
                null
            ),
            BookInProgress(
                4,
                "Маленькая хозяйка большого дома",
                "Джек Лондон",
                0,
                350,
                BookStatus.PLANNED_READ,
                null
            ),
            BookInProgress(
                5,
                "Театр",
                "Моэм",
                0,
                400,
                BookStatus.PLANNED_READ,
                null
            )
        )
    )
    val booksInProgress: StateFlow<List<BookInProgress>>
        get() = _booksInProgress
}