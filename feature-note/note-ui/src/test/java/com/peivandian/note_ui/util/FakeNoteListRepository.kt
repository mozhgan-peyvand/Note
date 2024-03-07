package com.peivandian.note_ui.util

import com.peivandian.note_domain.repositories.NoteRepository
import com.peivandian.note_models.NoteEntity
import com.peivandian.note_ui.models.noteEntity1
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeNoteListRepository() : NoteRepository {

    var noteList: Flow<List<NoteEntity>> = flowOf()

    override suspend fun insertTodo(noteEntity: NoteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getNoteById(id: Int): NoteEntity? {
       return noteEntity1
    }

    override fun getNotes(): Flow<List<NoteEntity>> {
        return noteList
    }
}