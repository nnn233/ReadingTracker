package com.example.readingtracker.data.mappers

import com.example.readingtracker.data.database.book_progress.BookWithProgress
import com.example.readingtracker.data.model.BookInProgress
import com.example.readingtracker.presentation.fragments.home.state_holders.BookHomeState
import com.example.readingtracker.presentation.fragments.home.state_holders.BookStatus

fun BookInProgress.toBookHomeState(): BookHomeState = BookHomeState(
    id = id,
    title = title,
    author = author,
    pages = pages,
    currentPage = currentPage,
    cover = cover,
    status = status
)

fun BookWithProgress.toBookInProgress(): BookInProgress = BookInProgress(
    id = book.id,
    title = book.title,
    author = book.author,
    currentPage = if (progresses.isNotEmpty()) progresses.last().currentPage ?: 0 else 0,
    pages = book.pages,
    status = if (progresses.isNotEmpty()) progresses.last().status else BookStatus.GENERAL,
    cover = book.cover
)