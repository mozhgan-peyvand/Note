package com.peivandian.note_models



sealed class NoteListEvents{

    data class  OnNoteClick(val noteEntity: NoteEntity): NoteListEvents()

    object OnAddNoteClick: NoteListEvents()

    data class OnSearchClick(var query: String): NoteListEvents()
}