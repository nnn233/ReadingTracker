package com.example.readingtracker.data.repository

import com.example.readingtracker.data.data_store.LocalGoals
import com.example.readingtracker.data.model.Goal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GoalsRepository(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    coroutineScope: CoroutineScope = CoroutineScope(defaultDispatcher),
    private val localGoals: LocalGoals
) {
    private val _goals = MutableStateFlow(Goal(null, null))
    val goals: Flow<Goal>
        get() = _goals

    init {
        coroutineScope.launch {
            localGoals.minutesCount.collect {
                _goals.value = _goals.value.copy(
                    dailyGoal = it ?: 0
                )
            }
        }
        coroutineScope.launch {
            localGoals.booksCount.collect {
                _goals.value = _goals.value.copy(
                    yearGoal = it ?: 0
                )
            }
        }
    }
}