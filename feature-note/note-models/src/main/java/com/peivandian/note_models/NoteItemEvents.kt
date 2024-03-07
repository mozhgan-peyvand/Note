package com.peivandian.note_models

sealed class NoteItemEvents{
    /*
    * what kind of event we could possibly send:
    * 1- they could change the text content of our title text
    * 2- they could change the text content of our description text
    * 3- we may have unsafe to do click
    * */
    data class OnTitleChange(val title: String): NoteItemEvents()
    data class OnDescriptionChange(val description: String): NoteItemEvents()
    object OnSaveTodoClick: NoteItemEvents()

    object OnBackClick: NoteItemEvents()
}