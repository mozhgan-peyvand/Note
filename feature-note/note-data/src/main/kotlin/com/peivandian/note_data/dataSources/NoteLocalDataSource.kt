package com.peivandian.note_data.dataSources

import com.peivandian.note_models.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteLocalDataSource {

    suspend fun insertNote (noteEntity: NoteEntity)

    suspend fun deleteNote(noteEntity: NoteEntity)

    suspend fun getNoteById(id:Int): NoteEntity?

    fun getNotes(): Flow<List<NoteEntity>>
}