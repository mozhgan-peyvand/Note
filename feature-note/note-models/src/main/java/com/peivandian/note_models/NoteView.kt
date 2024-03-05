package com.peivandian.note_models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class NoteView(
    val title: String,
    val description: String?,
    val schedule: String,
    val id: Int? =null
)

