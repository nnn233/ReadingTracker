package com.example.readingtracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.readingtracker.data.database.converters.BitmapConverter

@Database(
    entities = [BookEntity::class, ProgressEntity::class, ReviewEntity::class, DailyReadingTimeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(BitmapConverter::class)
abstract class ReadingTrackerDatabase : RoomDatabase() {

    abstract val progressDao: LocalProgressDataSource
    abstract val dailyReadingDao: LocalDailyTimeDataSource

    companion object {
        @Volatile
        private var INSTANCE: ReadingTrackerDatabase? = null

        fun getDatabase(context: Context): ReadingTrackerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReadingTrackerDatabase::class.java,
                    "reading_tracker_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}