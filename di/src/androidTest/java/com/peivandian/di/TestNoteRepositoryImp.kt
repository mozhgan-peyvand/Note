package com.peivandian.di
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.peivandian.di.models.noteEntity1
import com.peivandian.di.models.noteEntity2
import com.peivandian.note_data.dataSources.NoteDao
import com.peivandian.note_data.dataSources.NoteDataBase
import com.peivandian.note_data.dataSources.NoteLocalDataSourceImpl
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException


class TestNoteRepositoryImp {

    private lateinit var noteListDao: NoteDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        noteListDao = appDatabase.noteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun testGetNoteById() = runTest {

        // Arrange
        val noteListDataSource = NoteLocalDataSourceImpl(noteListDao)
        noteListDataSource.insertNote(noteEntity1)
        val expectedNoteDetails = noteEntity1

        // Act
        val actualNoteDetails = noteListDataSource.getNoteById(1)


        // Assert
        assertEquals(expectedNoteDetails, actualNoteDetails)
    }


    @Test
    fun testGetAllNotes() = runTest {

        // Arrange
        val noteListDataSource = NoteLocalDataSourceImpl(noteListDao)
        val expectedList = listOf(noteEntity1, noteEntity2)

        // Act
        noteListDataSource.insertNote(noteEntity1)
        noteListDataSource.insertNote(noteEntity2)
        val noteListFlow = noteListDataSource.getNotes()

        // Assert
        noteListFlow.test {
            assertEquals(expectedList, awaitItem())
        }
    }
}