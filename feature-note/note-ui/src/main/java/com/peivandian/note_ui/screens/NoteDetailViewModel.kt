package com.peivandian.note_ui.screens

import android.database.sqlite.SQLiteAbortException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peivandian.base.util.UiEvent
import com.peivandian.note_domain.usecases.local.GetLocalNoteById
import com.peivandian.note_domain.usecases.local.SetLocalNotes
import com.peivandian.note_models.NoteEntity
import com.peivandian.note_models.NoteItemEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val setLocalNotes: SetLocalNotes,
    private val getLocalNoteById: GetLocalNoteById,
) : ViewModel() {

    var note by mutableStateOf<NoteEntity?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getLocalNoteById()
    }

    fun getLocalNoteById() {
        val noteId = savedStateHandle.get<Int>("noteId")
        noteId?.let {
            if (it != -1) {
                viewModelScope.launch {
                    getLocalNoteById(id = it)?.let { noteView ->
                        title = noteView.title
                        description = noteView.description ?: ""
                        note = noteView
                    }
                }
            }

        }
    }

    fun onEvent(event: NoteItemEvents) {
        when (event) {
            is NoteItemEvents.OnTitleChange -> {
                title = event.title
            }

            is NoteItemEvents.OnDescriptionChange -> {
                description = event.description
            }

            is NoteItemEvents.OnSaveTodoClick -> {
                viewModelScope.launch {
                    try {
                        if (title.isBlank()) {
                            sendUiEvent(
                                UiEvent.ShowSnackbar(
                                    message = "the title cant be empty"
                                )
                            )
                            return@launch
                        } else {
                            setLocalNotes(
                                NoteEntity(
                                    title = title,
                                    description = description,
                                    id = note?.id
                                )
                            )
                            sendUiEvent(
                                UiEvent.ShowSnackbar(
                                    message = "It was done successfully"
                                )
                            )

                        }
                    } catch (e: SQLiteAbortException) {
                        e.printStackTrace()
                    }


                }
            }

            NoteItemEvents.OnBackClick -> {
                viewModelScope.launch {
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}