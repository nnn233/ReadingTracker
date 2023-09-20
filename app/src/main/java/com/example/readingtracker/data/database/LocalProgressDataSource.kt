package com.example.readingtracker.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow


@Dao
interface LocalProgressDataSource {
    @Transaction
    @Query("SELECT * FROM books_table")
    fun getAllBooksWithProgresses(): Flow<List<BookWithProgress>>

    @Transaction
    suspend fun insertBookWithProgresses(item: BookWithProgress) {
        insertBook(item.book)
        for (progress in item.progresses)
            insertProgress(progress)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: ProgressEntity)
}