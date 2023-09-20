package com.example.readingtracker.application.components

import android.app.Application
import android.content.Context
import com.example.readingtracker.data.database.ReadingTrackerDatabase

class ReadingTrackerApplication:Application() {
    private val database by lazy { ReadingTrackerDatabase.getDatabase(applicationContext) }
    val applicationComponent by lazy { ApplicationComponent(database) }
    companion object {
        fun get(context: Context): ReadingTrackerApplication = context.applicationContext as ReadingTrackerApplication
        lateinit var instance: ReadingTrackerApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}