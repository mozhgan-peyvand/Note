package com.peivandian.base.navigationHelper

sealed class AppGraph(val router: String) {
    object NoteGraph : AppGraph("noteGraph://noteListScreen")
}