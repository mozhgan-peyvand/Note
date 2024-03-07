package com.peivandian.note_domain.repositories

import com.peivandian.note_models.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertTodo (noteEntity: NoteEntity)

    suspend fun getNoteById(id:Int): NoteEntity?

    fun getNotes(): Flow<List<NoteEntity>>
}