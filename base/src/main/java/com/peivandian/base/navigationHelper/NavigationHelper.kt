package com.peivandian.base.navigationHelper

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.composable
import ir.part.sdk.ara.common.ui.view.navigationHelper.DeepLinkHelper

fun NavGraphBuilder.safeScreenInitial(
    sourceScreen: String,
    arguments: List<NamedNavArgument> = emptyList(),
    screenSetUp: @Composable (NavBackStackEntry) -> Unit,
    deepLink: List<NavDeepLink> = emptyList(),
    getArgumentName: List<String>? = emptyList()
) {
    composable(
        route = getArgument(sourceScreen, getArgumentName),
        arguments = arguments,
        deepLinks = deepLink
    ) { navBackStackEntry ->
        screenSetUp.invoke(navBackStackEntry)
    }
}

fun NavHostController.safeNavigate(
    destinationScreen: String,
    popUpTo: String? = null,
    inclusiveScreen: Boolean = false,
    args: List<Any>? = emptyList()
) {

    if (args?.isNotEmpty() == true) {
        navigate(route = passArgument(destinationScreen, args)) {
            if (popUpTo != null) {
                popUpTo(popUpTo) { inclusive = inclusiveScreen }
            }
        }
    } else {
        navigate(route = destinationScreen) {
            if (popUpTo != null) {
                popUpTo(popUpTo) { inclusive = inclusiveScreen }
            }
        }
    }
}

// TODO: need more Research
fun safeNavigate(
    navController: NavController,
    deepLink: DeepLinkHelper,
    popupId: Int = 0,
    inclusive: Boolean = true
) {
    try {
        if (popupId != 0) {
            val navOption = NavOptions.Builder().setPopUpTo(popupId, inclusive).build()
            navController.navigate(deepLink.uri, navOption)
        } else {
            navController.navigate(deepLink.uri)
        }
    } catch (e: Exception) {
//        Timber.tag("Navigate").d(e)
    }
}

fun passArgument(router: String, args: List<Any>): String {
    return buildString {
        append(router)
        args.forEach { arg ->
            append("/$arg")
        }
    }
}

fun getArgument(router: String, value: List<String>?): String {
    return buildString {
        append(router)
        value?.forEach { arg ->
            append("/{$arg}")
        }
    }
}

