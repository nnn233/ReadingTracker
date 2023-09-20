package com.example.readingtracker.domain.goals

import com.example.readingtracker.data.model.Goal
import com.example.readingtracker.data.repository.GoalsRepository
import kotlinx.coroutines.flow.Flow

class GetGoalUseCase(
    private val repository: GoalsRepository
) {
    operator fun invoke(): Flow<Goal> =
        repository.goals
}