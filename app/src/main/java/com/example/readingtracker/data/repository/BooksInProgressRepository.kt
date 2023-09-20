package com.example.readingtracker.data.repository

import com.example.readingtracker.BookFormat
import com.example.readingtracker.data.database.BookEntity
import com.example.readingtracker.data.database.BookWithProgress
import com.example.readingtracker.data.database.LocalProgressDataSource
import com.example.readingtracker.data.database.ProgressEntity
import com.example.readingtracker.data.mappers.toBookInProgress
import com.example.readingtracker.data.model.BookInProgress
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.GregorianCalendar

class BooksInProgressRepository(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val localDao: LocalProgressDataSource
) {
    val booksInProgress: Flow<List<BookInProgress>>
        get() = localDao.getAllBooksWithProgresses()
            .map { bookWithProgresses -> bookWithProgresses.map { it.toBookInProgress() } }

    init {
        CoroutineScope(defaultDispatcher).launch {
            add()
        }
    }

    suspend fun add() {
        withContext(defaultDispatcher) {
            localDao.insertBookWithProgresses(
                BookWithProgress(
                    BookEntity(
                        10,
                        "Гордость и предубеждение",
                        "Джейн Остин",
                        BookFormat.E_BOOK,
                        300,
                        null,
                        null
                    ),

                    listOf(
                        ProgressEntity(
                            0,
                            10,
                            115,
                            GregorianCalendar(2023, 8, 22).timeInMillis,
                            null,
                            reviewId = null
                        )
                    )
                ))
            localDao.insertBookWithProgresses(BookWithProgress(
                    BookEntity(
                        1,
                        "Богиня глюкозы",
                        "Женщина",
                        BookFormat.E_BOOK,
                        600,
                        null,
                        null
                    ),

                    listOf(
                        ProgressEntity(
                            0,
                            1,
                            10,
                            GregorianCalendar(2023, 9, 19).timeInMillis,
                            null,
                            reviewId = null
                        )
                    )
                )
            )
            localDao.insertBookWithProgresses(BookWithProgress(
                BookEntity(
                    2,
                    "Жутко громко",
                    "Джонатан",
                    BookFormat.E_BOOK,
                    739,
                    null,
                    null
                ),

                listOf(
                    ProgressEntity(
                        bookId = 2,
                        currentPage = 10,
                        startDate = GregorianCalendar(2023, 9, 19).timeInMillis,
                        finishDate = null,
                        reviewId = null
                    )
                )
            )
            )
            localDao.insertBookWithProgresses(BookWithProgress(
                BookEntity(
                    3,
                    "Замок из стекла",
                    "Джанет Уолс",
                    BookFormat.PAPER_BOOK,
                    355,
                    null,
                    null
                ),

                listOf(
                    ProgressEntity(
                        bookId = 3,
                        currentPage = 10,
                        startDate = GregorianCalendar(2023, 9, 19).timeInMillis,
                        finishDate = null,
                        reviewId = null
                    )
                )
            )
            )
            localDao.insertBookWithProgresses(BookWithProgress(
                BookEntity(
                    4,
                    "Трудно быть богом",
                    "Братья Стругацкие",
                    BookFormat.E_BOOK,
                    600,
                    null,
                    null
                ),

                listOf(
                    ProgressEntity(
                        bookId = 4,
                        currentPage = 10,
                        startDate = GregorianCalendar(2023, 9, 19).timeInMillis,
                        finishDate = null,
                        reviewId = null
                    )
                )
            )
            )
        }
    }
}