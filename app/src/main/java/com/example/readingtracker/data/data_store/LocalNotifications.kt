package com.example.readingtracker.data.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalNotifications(private val dataStore: DataStore<Preferences>) {
    private val key = longPreferencesKey("notification_time")

    val notificationTime: Flow<Long?> = dataStore.data
        .map { preferences ->
            preferences[key]
        }

    suspend fun setNotification(time:Long) {
        dataStore.edit { notifications ->
            notifications[key] = time
        }
    }
}