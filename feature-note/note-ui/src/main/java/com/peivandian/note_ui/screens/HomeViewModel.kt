package com.peivandian.note_ui.screens

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val event = MutableStateFlow<Event>(Event.None)

    fun handleDeeplink(uri: Uri) {
        event.update { Event.NavigateWithDeeplink(uri) }
    }

    fun consumeEvent() {
        event.update { Event.None }
    }
}
sealed interface Event {
    data class NavigateWithDeeplink(val deeplink: Uri) : Event
    object None : Event
}
