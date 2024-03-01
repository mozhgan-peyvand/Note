package com.peivandian.note_ui.util.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.peivandian.base.navigationHelper.AppGraph
import com.peivandian.note_ui.screens.NoteDetailScreen
import com.peivandian.note_ui.screens.NoteListScreen


fun NavGraphBuilder.addNoteGraph(navController: NavHostController) {
    navigation(
        route = AppGraph.NoteGraph.router,
        startDestination = NoteRouter.NoteListScreen.router
    ) {

        noteListScreen {
            NoteListScreen(navController)
        }

        noteDetailScreen {
            NoteDetailScreen(navController)

        }


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