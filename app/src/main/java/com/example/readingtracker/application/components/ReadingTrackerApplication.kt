package com.example.readingtracker.application.components

import android.app.Application
import android.content.Context

class ReadingTrackerApplication:Application() {

    val applicationComponent by lazy { ApplicationComponent() }
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