package com.peivandian.note_data.dataSources

import com.peivandian.note_models.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteLocalDataSourceImpl @Inject constructor( private val noteDao: NoteDao) :
    NoteLocalDataSource {
    override suspend fun insertNote(noteEntity: NoteEntity) {
        noteDao.insertNote(noteEntity)
    }

    override suspend fun deleteNote(noteEntity: NoteEntity) {
        noteDao.deleteNote(noteEntity)
    }

    override suspend fun getNoteById(id: Int): NoteEntity? {
       return noteDao.getNoteById(id)
    }

    override fun getNotes(): Flow<List<NoteEntity>> {
        return  noteDao.getNotes()
    }
}