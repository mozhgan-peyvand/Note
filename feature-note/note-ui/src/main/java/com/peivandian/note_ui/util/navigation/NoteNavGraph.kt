package com.peivandian.note_ui.util.navigation

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


fun NavGraphBuilder.addNoteGraph(
    navController: NavHostController,
    setHasBottomBar: (Boolean) -> Unit,
    addNoteClick: Boolean,
    setAddNote: (Boolean) -> Unit
) {
    navigation(
        route = AppGraph.NoteGraph.router,
        startDestination = NoteRouter.NoteListScreen.router
    ) {

        composable(
            NoteRouter.NoteListScreen.router,
            deepLinks = listOf(navDeepLink {
                uriPattern = "note://" + NoteRouter.NoteListScreen.router
            })
            ) {
            NoteListScreen(
                onNavigate = {
                    navController.navigate(it.route)
                },
                setHasBottomBar = setHasBottomBar,
                addNoteClick = addNoteClick,
                setAddNote = setAddNote
            )
        }
        composable(NoteRouter.NoteDetailScreen.router + "?noteId={noteId}",
            arguments = listOf(
                navArgument(name = "noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            NoteDetailScreen(
                onPopBackStack = { navController.popBackStack() },
                setHasBottomBar = setHasBottomBar
            )
        }
//        noteListScreen {
//            NoteListScreen(navController,setHasBottomBar =setHasBottomBar)
//        }
//
//        noteDetailScreen {
//            NoteDetailScreen(navController,setHasBottomBar = setHasBottomBar)
//
//        }


//        forgetPasswordVerificationScreen {
//            val forgetPasswordVerificationViewModel =
//                UserComponent.builder(LocalContext.current as ComponentProviderActivity)
//                    .getForgetPasswordVerificationViewModel()
//            ForgetPasswordVerificationScreen(
//                navigateToLogin = { navController.navigateToLoginScreen() },
//                navigateUp = { navController.navigateToForgetPasswordScreen() },
//                forgetPasswordVerificationViewModel = forgetPasswordVerificationViewModel
//            )
//        }

    }
}

