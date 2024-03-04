package com.peivandian.note_data.dataSources

interface NoteDataBase {
    fun noteDao(): NoteDao
}