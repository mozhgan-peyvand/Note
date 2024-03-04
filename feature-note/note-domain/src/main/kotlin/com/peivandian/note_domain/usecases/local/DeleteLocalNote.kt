package com.peivandian.note_domain.usecases.local

import com.peivandian.note_domain.repositories.NoteRepository
import com.peivandian.note_models.NoteEntity
import javax.inject.Inject

class DeleteLocalNote @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(noteEntity: NoteEntity) {
        noteRepository.deleteNote(noteEntity)
    }

}