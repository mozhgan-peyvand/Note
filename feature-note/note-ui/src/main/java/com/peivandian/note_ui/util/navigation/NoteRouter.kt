package com.peivandian.note_ui.util.navigation

import com.peivandian.base.util.NoteUiModules


val moduleName = NoteUiModules.MODULE_UI_NOTE.value

sealed class NoteRouter(val router: String) {
    object NoteListScreen : NoteRouter(router = "$moduleName://noteListScreen")
    object NoteDetailScreen : NoteRouter(router = "$moduleName://noteDetailScreen")
}