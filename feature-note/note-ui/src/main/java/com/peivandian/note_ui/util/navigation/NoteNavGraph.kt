package com.peivandian.note_ui.util.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
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
        startDestination = NoteRouter.NoteDetailScreen.router
    ) {

        noteListScreen {
            NoteListScreen(navController,setHasBottomBar =setHasBottomBar)
        }

        noteDetailScreen {
            NoteDetailScreen(navController,setHasBottomBar = setHasBottomBar)

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