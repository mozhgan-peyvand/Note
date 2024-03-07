package com.peivandian.note_models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NoteEntity(
    val title: String,
    val description: String?,
    @PrimaryKey val id: Int? =null
)