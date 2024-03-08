package com.peivandian.note_ui.util.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.peivandian.base.navigationHelper.AppGraph
import com.peivandian.note_ui.screens.NoteDetailScreen
import com.peivandian.note_ui.screens.NoteListScreen
import com.peivandian.note_ui.util.ui.SharedNoteScreen


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.addNoteGraph(
    navController: NavHostController,
    setHasBottomBar: (Boolean) -> Unit,
    addNoteClick: Boolean,
    setAddNote: (Boolean) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    navigation(
        route = AppGraph.NoteGraph.router,
        startDestination = NoteRouter.NoteListScreen.router
    ) {

        composable(
            NoteRouter.NoteListScreen.router,
            deepLinks = listOf(navDeepLink {
                uriPattern = NoteRouter.NoteListScreen.router
            })
            ) {
            setHasBottomBar.invoke(true)

            NoteListScreen(
                onNavigate = {
                    navController.navigate(it.route)
                },
                addNoteClick = addNoteClick,
                setAddNote = setAddNote,
                snackbarHostState = snackbarHostState,
                navController = navController
            )
        }
        composable(
            route = NoteRouter.NoteDetailScreen.router + "?noteId={noteId}",
            arguments = listOf(
                navArgument(name = "noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            setHasBottomBar.invoke(false)

            NoteDetailScreen(
                onPopBackStack = { navController.popBackStack() },
                snackbarHostState = snackbarHostState
            )
        }

        composable(
            route = NoteRouter.SharedNoteScreen.router
        ){
            SharedNoteScreen()
        }
    }
}

