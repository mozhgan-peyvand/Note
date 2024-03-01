package com.peivandian.note_ui.util.navigation

import androidx.navigation.NavHostController
import com.peivandian.base.navigationHelper.safeNavigate

fun NavHostController.navigateToNoteDetailScreen() = safeNavigate(
    NoteRouter.NoteDetailScreen.router
)


//fun NavHostController.navigateLoginScreenWithPopUp() =
//    safeNavigate(
//        destinationScreen = UserRouter.UserLoginScreen.router,
//        popUpTo = UserRouter.UserLoginScreen.router,
//        inclusiveScreen = true
//    )
