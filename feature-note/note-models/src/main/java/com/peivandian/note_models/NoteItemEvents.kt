package com.peivandian.note_models

sealed class NoteItemEvents{
    data class OnTitleChange(val title: String): NoteItemEvents()
    data class OnDescriptionChange(val description: String): NoteItemEvents()
    object OnSaveTodoClick: NoteItemEvents()

    object OnBackClick: NoteItemEvents()
}