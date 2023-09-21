package com.example.readingtracker.data.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "notifications")

class LocalNotifications(private val context: Context) {
    private val notification = longPreferencesKey("notification_time")

    val notificationTime: Flow<Long?> = context.dataStore.data
        .map { preferences ->
            preferences[notification]
        }

    suspend fun setNotification(time:Long) {
        context.dataStore.edit { notifications ->
            notifications[notification] = time
        }
    }
}