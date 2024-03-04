package com.peivandian.note_ui.util.navigation

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.peivandian.base.navigationHelper.AppGraph
import com.peivandian.note_ui.screens.NoteDetailScreen
import com.peivandian.note_ui.screens.NoteListScreen


fun NavGraphBuilder.addNoteGraph(
    navController: NavHostController,
    setHasBottomBar: (Boolean) -> Unit
) {
    navigation(
        route = AppGraph.NoteGraph.router,
        startDestination = NoteRouter.NoteListScreen.router
    ) {

        composable(NoteRouter.NoteListScreen.router) {
            NoteListScreen(
                onNavigate = {
                    navController.navigate(it.route)
                },
                setHasBottomBar = setHasBottomBar
            )
        }
        composable(NoteRouter.NoteDetailScreen.router + "?todoId={todoId}",
            arguments = listOf(
                navArgument(name = "todoId") {
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

