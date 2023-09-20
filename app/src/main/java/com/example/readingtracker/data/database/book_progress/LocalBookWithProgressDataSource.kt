package com.example.readingtracker.data.database.book_progress

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.readingtracker.data.database.book.BookEntity
import com.example.readingtracker.data.database.progress.ProgressEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface LocalBookWithProgressDataSource {
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