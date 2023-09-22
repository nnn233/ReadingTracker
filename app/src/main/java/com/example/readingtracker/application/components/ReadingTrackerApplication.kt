package com.example.readingtracker.application.components

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.readingtracker.data.database.ReadingTrackerDatabase

class ReadingTrackerApplication : Application() {
    private val database by lazy { ReadingTrackerDatabase.getDatabase(applicationContext) }
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    val applicationComponent by lazy { ApplicationComponent(database, dataStore) }

    companion object {
        fun get(context: Context): ReadingTrackerApplication =
            context.applicationContext as ReadingTrackerApplication

        lateinit var instance: ReadingTrackerApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}