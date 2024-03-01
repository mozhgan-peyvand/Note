package com.peivandian.note_ui.util.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.peivandian.base.navigationHelper.safeScreenInitial


fun NavGraphBuilder.noteListScreen(
    screen: @Composable (NavBackStackEntry) -> Unit
) {
    safeScreenInitial(
        sourceScreen = NoteRouter.NoteListScreen.router,
        screenSetUp = {
            screen.invoke(it)
        }
    )
}

fun NavGraphBuilder.noteDetailScreen(
    screen: @Composable (NavBackStackEntry) -> Unit
) {
    safeScreenInitial(
        sourceScreen = NoteRouter.NoteDetailScreen.router,
        screenSetUp = {
            screen.invoke(it)
        }
    )
}