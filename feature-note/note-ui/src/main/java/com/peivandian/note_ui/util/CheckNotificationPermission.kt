package com.peivandian.note_ui.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.content.ContextCompat
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun CheckPermission(
    setDialogState: (Boolean) -> Unit,
    setHasLocationPermission: (Boolean) -> Unit
) {
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            setHasLocationPermission.invoke(true)
            setDialogState.invoke(true)
        }
    }

    LaunchedEffect(key1 = true) {
        scope.launch {
            launcher.launch(
                Manifest.permission.POST_NOTIFICATIONS
            )
        }

    }

}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun checkForLocationPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.POST_NOTIFICATIONS
    ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
}
