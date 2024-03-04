package com.peivandian.note_models



sealed class NoteListEvents{
    /*
    * in the mvvm we can have a event class for each of screen
    * now:
    * for this event class we need to think about what kind of
    * user interactions could we possibly have on this to do list screen
    * 1-well we could for example click on this delete icon,
    * 2-we could for example toggle the done state of our to do ,
    * 3-we could click on an item on a to do item to edit it,
    * 4-we could click on this floating action button to add a new to do,
    * 5-we could click undo which is also new even that we sent to our review model
    * */

    // deleting a to do so that will be a data class
    data class OnDeleteNote(val noteEntity: NoteEntity): NoteListEvents()
    //ond done change so when we actually want to change the done state of a specific to do ,toggle that
    data class OnDoneChange(val noteEntity: NoteEntity,val isDone: Boolean): NoteListEvents()
    //then we actually can undo the deletion of it to do that will just be
    //an object it does not need any parameters because we will just cache the
    //recently deleted to do in view model and then just restore it if we click that
    object OnUndoDeleteClick: NoteListEvents()
    //click when we click on to do item to get to its detail view
    data class  OnNoteClick(val noteEntity: NoteEntity): NoteListEvents()
    //add a new to do so that actually an object because that does not
    //need any parameters um on add to do click to do list event
    object OnAddNoteClick: NoteListEvents()
}