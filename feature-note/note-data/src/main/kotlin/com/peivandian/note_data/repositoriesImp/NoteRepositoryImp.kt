package com.peivandian.note_data.repositoriesImp

import com.peivandian.note_data.dataSources.NoteLocalDataSource
import com.peivandian.note_domain.repositories.NoteRepository
import com.peivandian.note_models.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteLocalDataSource: NoteLocalDataSource
) : NoteRepository {

    override suspend fun insertTodo(noteEntity: NoteEntity) {
        noteLocalDataSource.insertNote(noteEntity)
    }

    override suspend fun getNoteById(id: Int): NoteEntity? {
        return noteLocalDataSource.getNoteById(id)
    }

    override fun getNotes(): Flow<List<NoteEntity>> {
        return noteLocalDataSource.getNotes()
    }
}