package com.example.readingtracker.data.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalGoals(private val dataStore: DataStore<Preferences>) {
    private val keyBooks = intPreferencesKey("books_goal")
    private val keyMinutesPerDay = intPreferencesKey("minutes_per_day_goal")

    val booksCount: Flow<Int?> = dataStore.data
        .map { preferences ->
            preferences[keyBooks]
        }

    suspend fun setBooksCount(count:Int) {
        dataStore.edit { books ->
            books[keyBooks] = count
        }
    }

    val minutesCount: Flow<Int?> = dataStore.data
        .map { preferences ->
            preferences[keyMinutesPerDay]
        }

    suspend fun setMinutesCount(count:Int) {
        dataStore.edit { books ->
            books[keyMinutesPerDay] = count
        }
    }
}