package com.peivandian.note_ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peivandian.base.util.UiEvent
import com.peivandian.note_domain.usecases.local.DeleteLocalNote
import com.peivandian.note_domain.usecases.local.GetLocalNoteById
import com.peivandian.note_domain.usecases.local.GetLocalNotes
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
    savedStateHandle: SavedStateHandle,
    private val getLocalNotes: GetLocalNotes,
    private val setLocalNotes: SetLocalNotes,
    private val getLocalNoteById: GetLocalNoteById,
    private val deleteLocalNote: DeleteLocalNote

): ViewModel() {

    var todo by mutableStateOf<NoteEntity?>(null)
        private set

    /*
    * private set:
    * we can change just in this view model
    * but we can reed it outside
    * */
    var title by mutableStateOf("")
        private set

    /*
    * by:
    * we can easily assign the state without using that value all
    *
    * */
    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        /*
        here we first want to check did we actually open this add edit
        page by clicking on an existing to do or by clicking on adding
        a new to do because if we want to open or if we open this from
        an existing to do then we want to load this to do from the
        database by its id so then we would get the to do id using our
        saved state handle that is an integer the navigation argument is
        called to do id and
        * */
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if (todoId != -1) {
            viewModelScope.launch {
                getLocalNoteById(id = todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: ""
                    this@NoteDetailViewModel.todo = todo
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
                    if (title.isBlank()){
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "the title cant be empty"
                        ))
                        return@launch
                    }
                    setLocalNotes(
                        NoteEntity(
                            title = title,
                            description = description,
                            isDone = todo?.isDone ?: false,
                            id = todo?.id
                        )
                    )
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