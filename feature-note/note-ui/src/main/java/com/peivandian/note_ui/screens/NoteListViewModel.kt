package com.peivandian.note_ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peivandian.base.util.UiEvent
import com.peivandian.base.util.collectOn
import com.peivandian.note_domain.usecases.local.GetLocalNotes
import com.peivandian.note_domain.usecases.local.SetLocalNotes
import com.peivandian.note_models.NoteEntity
import com.peivandian.note_models.NoteListEvents
import com.peivandian.note_ui.util.navigation.NoteRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getLocalNotes: GetLocalNotes
) : ViewModel() {

    private val _searchedList = MutableStateFlow<MutableList<NoteEntity>>(mutableListOf())
    val searchedList: StateFlow<MutableList<NoteEntity>> = _searchedList.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val notes = getLocalNotes()

    fun onEvent(event: NoteListEvents) {
        when (event) {

            is NoteListEvents.OnNoteClick -> {
                sendUiEvent(UiEvent.Navigate(NoteRouter.NoteDetailScreen.router + "?noteId=${event.noteEntity.id}"))
            }

            is NoteListEvents.OnAddNoteClick -> {
                sendUiEvent(UiEvent.Navigate(NoteRouter.NoteDetailScreen.router))
            }

            is NoteListEvents.OnSearchClick -> {
                searchNote(event.query)
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    private fun searchNote(query: String) {

        viewModelScope.launch {
            notes.collectOn(viewModelScope) { noteList ->
                _searchedList.update {
                    noteList.filter { note ->
                        note.title.contains(query) || note.description?.contains(query) == true
                    }.toMutableList()
                }
            }
        }
    }

}