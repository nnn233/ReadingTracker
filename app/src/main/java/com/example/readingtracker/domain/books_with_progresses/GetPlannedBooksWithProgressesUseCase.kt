package com.example.readingtracker.domain.books_with_progresses

import com.example.readingtracker.data.mappers.toBookHomeState
import com.example.readingtracker.data.repository.BooksInProgressRepository
import com.example.readingtracker.presentation.fragments.home.state_holders.BookHomeState
import com.example.readingtracker.presentation.fragments.home.state_holders.BookStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPlannedBooksWithProgressesUseCase(
    private val repository: BooksInProgressRepository
) {
    suspend operator fun invoke(): Flow<List<BookHomeState>> =
        repository.getAllBooksWithProgresses().map { list ->
            list.filter { it.status == BookStatus.PLANNED_READ }
                .map { it.toBookHomeState() }
        }
}