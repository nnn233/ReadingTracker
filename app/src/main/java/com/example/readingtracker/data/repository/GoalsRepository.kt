package com.example.readingtracker.data.repository

import com.example.readingtracker.data.model.Goal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GoalsRepository(private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default) {
    private val _goals : MutableStateFlow<Goal> = MutableStateFlow(Goal(30, 50))
    val goals: StateFlow<Goal>
        get() = _goals
}