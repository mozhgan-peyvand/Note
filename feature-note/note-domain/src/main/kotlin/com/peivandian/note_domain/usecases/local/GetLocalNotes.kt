package com.peivandian.note_domain.usecases.local

import com.peivandian.note_domain.repositories.NoteRepository
import com.peivandian.note_models.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalNotes @Inject constructor(
    private val noteRepository: NoteRepository
){

    operator fun invoke(): Flow<List<NoteEntity>> {
        return noteRepository.getNotes()
    }

}