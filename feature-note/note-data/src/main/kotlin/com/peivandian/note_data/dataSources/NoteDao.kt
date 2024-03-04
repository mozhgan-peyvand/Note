package com.peivandian.note_data.dataSources

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peivandian.note_models.NoteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    //on conflict is use for what was your strategy when insert an
    //id that exist currently in database
    //and we use of replace that mean when we have that id we should
    //update it and replace with new data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote (noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM NoteEntity WHERE id = :id")
    suspend fun getNoteById(id:Int): NoteEntity?


    //we use of flow becuse it is cool and when list change
    //we have new data and real time
    @Query("SELECT * FROM NoteEntity")
    fun getNotes(): Flow<List<NoteEntity>>
}