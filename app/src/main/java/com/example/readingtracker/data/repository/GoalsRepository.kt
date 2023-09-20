package com.example.readingtracker.data.repository

import com.example.readingtracker.data.model.Goal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class GoalsRepository(private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default) {
    private val _goals = MutableStateFlow(Goal(30, 50))
    val goals: Flow<Goal>
        get() = _goals
}