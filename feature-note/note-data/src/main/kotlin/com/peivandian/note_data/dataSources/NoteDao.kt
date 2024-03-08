package com.peivandian.note_data.dataSources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peivandian.note_models.NoteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote (noteEntity: NoteEntity)

    @Query("SELECT * FROM NoteEntity WHERE id = :id")
    suspend fun getNoteById(id:Int): NoteEntity?

    @Query("SELECT * FROM NoteEntity")
    fun getNotes(): Flow<List<NoteEntity>>
}