package com.example.readingtracker.data.mappers

import com.example.readingtracker.data.model.BookInProgress
import com.example.readingtracker.presentation.fragments.home.state_holders.BookHomeState

fun BookInProgress.toBookHomeState(): BookHomeState = BookHomeState(
    id = id,
    title = title,
    author = author,
    pages = pages,
    currentPage = currentPage,
    cover = cover,
    status = status
)