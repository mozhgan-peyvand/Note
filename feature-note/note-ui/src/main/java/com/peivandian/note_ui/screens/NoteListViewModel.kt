package com.peivandian.note_ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peivandian.base.util.UiEvent
import com.peivandian.note_domain.usecases.local.GetLocalNotes
import com.peivandian.note_domain.usecases.local.SetLocalNotes
import com.peivandian.note_models.NoteEntity
import com.peivandian.note_models.NoteListEvents
import com.peivandian.note_ui.util.navigation.NoteRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getLocalNotes: GetLocalNotes
) : ViewModel() {

    val notes = getLocalNotes()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NoteListEvents) {
        when (event) {

            is NoteListEvents.OnNoteClick -> {

                sendUiEvent(UiEvent.Navigate(NoteRouter.NoteDetailScreen.router + "?noteId=${event.noteEntity.id}"))
            }

            is NoteListEvents.OnAddNoteClick -> {

                sendUiEvent(UiEvent.Navigate(NoteRouter.NoteDetailScreen.router))
            }

        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}