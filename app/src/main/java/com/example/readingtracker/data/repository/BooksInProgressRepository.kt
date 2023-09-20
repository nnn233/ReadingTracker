package com.example.readingtracker.data.repository

import com.example.readingtracker.data.database.book_progress.LocalBookWithProgressDataSource
import com.example.readingtracker.data.mappers.toBookInProgress
import com.example.readingtracker.data.model.BookInProgress
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class BooksInProgressRepository(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val localDao: LocalBookWithProgressDataSource
) {
    suspend fun getAllBooksWithProgresses(): Flow<List<BookInProgress>> =
        withContext(defaultDispatcher) {
            return@withContext localDao.getAllBooksWithProgresses()
                .map { bookWithProgresses ->
                    bookWithProgresses.map { it.toBookInProgress() }
                }
        }
}