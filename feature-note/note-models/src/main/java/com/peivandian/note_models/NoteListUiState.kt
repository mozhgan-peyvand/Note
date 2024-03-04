package com.peivandian.note_models

import com.peivandian.base.util.Exceptions


data class NoteListUiState(
    val noteList: List<NoteEntity> = emptyList(),
    val error: Exceptions? = null,
    val isLoading: Boolean = false
)