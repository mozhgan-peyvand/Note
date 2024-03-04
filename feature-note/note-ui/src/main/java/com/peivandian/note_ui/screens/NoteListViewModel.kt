package com.peivandian.note_ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peivandian.base.util.UiEvent
import com.peivandian.note_domain.usecases.local.DeleteLocalNote
import com.peivandian.note_domain.usecases.local.GetLocalNoteById
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
    private val getLocalNotes: GetLocalNotes,
    private val setLocalNotes: SetLocalNotes,
    private val getLocalNoteById: GetLocalNoteById,
    private val deleteLocalNote: DeleteLocalNote
) : ViewModel() {

    /*
* in viewModel we will new put business logic we will now
* call our repository functions and we will now have our
* state variable.okey what kind of state do we actually now
* need here? lets first think about the to do list screen.
* what kind of state would make sense there if we open this
* here then in the end that s really just a single variable
* in this case and that is the list of todos that we actually
* want to display that s the only value on the screen that can
* potentially change over time because of course at the beginning
* the list is empty then we load it from the db we update the
* state with the list that we got form the db and then we
* reflect that and show that in the ui so
* */


    /*
    * in the view model we always define mutable version so
    * that s the version we can send event into and then we
    * also define an immutable version of that so a version
    * where we can send event into and that immutable version will
    * be exposed to the ui layer so that the ui layer can receive
    * events but not send new event into that so thats how its
    * supposed to be
    * */

    val notes = getLocalNotes()

    /*
     we also need something else and that is a so called channel or
     if you want you can also use a shared flow so lets open the
     app again here:
     a channel is actually used if we want to send one time events
     or shared flow will in the end do the same thing channels
     are meant to be used with single observers and shared flow can
     be shared so multiple observers are acceptable there but you can
     use both for the same use case so what does one time even mean
     here it means well a thing that only happens once like if we
     want to show a snack bar here if we want to undo that like
     these are one time events that we actually dont want to assign
     to a new state because as soon as we have some kind of state
     in a state variable then that means the state will also be
     kapt on screen rotations but if we actually delete this to do
     item and we show the snack bar which is just one a one time,
     one time thing and we rotate the device we do nt want to see
     the snack bar again but if we would have that as a state
     it would fire off again and we would see the snapper again for
     maybe error messages we had in the past or deleted items we
     had in the past so that s why we need a channel here to just
     send these on time events another use case would be to navigate
     if we actually click this button here then what will happen
     is our view model will send a navigate event to our ui and
     the ui will then call the navigate function of our nav controller
     that s also just a one time thing when we click the button we just
     want to do that once we dont want to do it again on screen rotations
     so just to make this clear what state what one time events here
     actually are will here use a channel because we only have
     a single observer lets call this:
    * */
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    //that is used to cache the recently deleted to do and if we then click undo we simply
    //take this and insert it back into our database
    private var deletedNote: NoteEntity? = null

    //now implement a function on event so that would be the function that we
    //trigger from the ui given such a to do list event and in here we can
    //now very easily distinguish what kind of event that is and depending on
    //that we just execute a different function or just some different type of
    // code:



    //that is used to cache the recently deleted to do and if we then click undo we simply
    //take this and insert it back into our database
    private var deletedTodo: NoteEntity? = null

    //now implement a function on event so that would be the function that we
    //trigger from the ui given such a to do list event and in here we can
    //now very easily distinguish what kind of event that is and depending on
    //that we just execute a different function or just some different type of
    // code:
    fun onEvent(event: NoteListEvents) {
        when (event) {
            is NoteListEvents.OnNoteClick -> {
                //we also want to attach the id of the to do item we clicked on because if we click
                //on an existing to do item we of course want to load that item on the add
                //edit to do screen because otherwise it would just have empty text fields it
                //would assume we want to add a new to do but if it exists we want to edit the
                //existing one instead so : route to do + edit to do
                sendUiEvent(UiEvent.Navigate(NoteRouter.NoteDetailScreen.router + "?noteId=${event.noteEntity.id}"))
            }
            is NoteListEvents.OnAddNoteClick -> {
                //here with click on add i want to send an event into channel that define of shared flow if use that
                // we click that button we want to get to that route
                sendUiEvent(UiEvent.Navigate(NoteRouter.NoteDetailScreen.router))
            }
            is NoteListEvents.OnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        setLocalNotes(todo)
                    }
                }
            }
            is NoteListEvents.OnDeleteNote -> {
                viewModelScope.launch {
                    deletedTodo = event.noteEntity
                    deleteLocalNote(event.noteEntity)
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            message = "Todo deleted",
                            action = "Undo"
                        )
                    )
                }
            }
            is NoteListEvents.OnDoneChange -> {
                //updata change database with the new boolean
                viewModelScope.launch {
                    setLocalNotes(
                        //so we will use the to do we actually passed for the event that will come from
                        //our ui layer and we copy that so we keep its id that way it will be updated
                        event.noteEntity.copy(
                            //done state that we also received from the ui
                            isDone = event.isDone
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }































//    private val _uiState = MutableStateFlow(NoteListUiState())
//    val uiState: StateFlow<NoteListUiState> = _uiState.asStateFlow()
//
//    init {
//        getLocalNoteList()
//    }
//
//    private fun getLocalNoteList() {
//        getLocalNotes().collectOn(viewModelScope) { noteList ->
//            _uiState.update { it.copy(noteList = noteList) }
//        }
//    }
//
//    fun addNoteList

}