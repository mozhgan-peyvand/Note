package com.peivandian.note

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.shapes.OvalShape
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.peivandian.base.R
import com.peivandian.base.navigationHelper.AppGraph
import com.peivandian.base.theme.ColorBlue100
import com.peivandian.base.theme.NoteTheme
import com.peivandian.note_ui.util.navigation.addNoteGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //initializing the default selected item
            var navigationSelectedItem by remember {
                mutableStateOf(0)
            }
            var hasBottomBar by remember {
                mutableStateOf(true)
            }
            var addNoteClick by remember {
                mutableStateOf(false)
            }
            navHostController = rememberNavController()
            NoteTheme {
                // A surface container using the 'background' color from the theme

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
//                        NavigationBar {
//                            //getting the list of bottom navigation items for our data class
//                            BottomNavigationItem().bottomNavigationItems()
//                                .forEachIndexed { index, navigationItem ->
//
//                                    //iterating all items with their respective indexes
//                                    NavigationBarItem(
//                                        selected = index == navigationSelectedItem,
//                                        label = {
//                                            Text(navigationItem.label)
//                                        },
//                                        icon = {
//                                            Icon(
//                                                navigationItem.icon,
//                                                contentDescription = navigationItem.label
//                                            )
//                                        },
//                                        onClick = {
//                                            navigationSelectedItem = index
//                                            navHostController.navigate(navigationItem.route) {
//                                                popUpTo(navHostController.graph.findStartDestination().id) {
//                                                    saveState = true
//                                                }
//                                                launchSingleTop = true
//                                                restoreState = true
//                                            }
//                                        }
//                                    )
//                                }
//                        }
                        if (hasBottomBar) {
                            BottomAppBar(
                                actions = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Default.Share,
                                            contentDescription = "Share contact"
                                        )
                                    }
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Default.FavoriteBorder,
                                            contentDescription = "Mark as favorite"
                                        )
                                    }
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Default.Email,
                                            contentDescription = "Email contact"
                                        )
                                    }
                                },
                                floatingActionButton = {
                                    FloatingActionButton(
                                        onClick = { addNoteClick = true },
                                        containerColor = ColorBlue100,
                                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.spacing_14x)),
                                        modifier = Modifier.padding(
                                            horizontal = dimensionResource(id = R.dimen.spacing_2x)
                                        ),
                                    ) {
                                        Icon(
                                            modifier = Modifier.padding(
                                                horizontal = dimensionResource(
                                                    id = R.dimen.spacing_2x
                                                )
                                            ),
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "Call contact",
                                            tint = Color.White
                                        )
                                    }
                                }


                            )
                        }
                    }) { paddingValues ->
                    //We need to setup our NavHost in here
                    NoteNavigation(
                        navHostController = navHostController,
                        paddingValues = paddingValues,
                        addNoteClick = addNoteClick,
                        setHasBottomBar = {
                            hasBottomBar = it
                        },
                        setAddNote = { addNoteClick = it }
                    )

                }
            }
        }
    }


    companion object {
        const val CHANNEL_ID = "reminder_id"
        private var TAG = "MainActivity"
        const val REQUEST_CODE_NOTIFICATION_PERMISSIONS = 11

    }

    @Composable
    fun NoteNavigation(
        navHostController: NavHostController,
        paddingValues: PaddingValues,
        setHasBottomBar: (Boolean) -> Unit,
        addNoteClick: Boolean,
        setAddNote: (Boolean) -> Unit
    ) {
        NavHost(
            navController = navHostController,
            startDestination = AppGraph.NoteGraph.router,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            addNoteGraph(
                navController = navHostController,
                setHasBottomBar = setHasBottomBar,
                addNoteClick = addNoteClick,
                setAddNote = setAddNote
            )
        }
    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun getNotificationPermissions() {
//        try {
//            // Check if the app already has the permissions.
//            val hasAccessNotificationPolicyPermission =
//                checkSelfPermission(android.Manifest.permission.ACCESS_NOTIFICATION_POLICY) == PackageManager.PERMISSION_GRANTED
//            val hasPostNotificationsPermission =
//                checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
//
//            // If the app doesn't have the permissions, request them.
//            when {
//                !hasAccessNotificationPolicyPermission || !hasPostNotificationsPermission -> {
//                    // Request the permissions.
//                    ActivityCompat.requestPermissions(
//                        this,
//                        arrayOf(
//                            android.Manifest.permission.ACCESS_NOTIFICATION_POLICY,
//                            android.Manifest.permission.POST_NOTIFICATIONS
//                        ),
//                        REQUEST_CODE_NOTIFICATION_PERMISSIONS
//                    )
//                }
//                else -> {
//                    // proceed
//                    Log.d(TAG, "Notification Permissions : previously granted successfully")
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    @Deprecated("Deprecated in Java")
//    @RequiresApi(Build.VERSION_CODES.M)
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        when (requestCode) {
//            // Check if the user granted the permissions.
//            REQUEST_CODE_NOTIFICATION_PERMISSIONS -> {
//                val hasAccessNotificationPolicyPermission =
//                    grantResults[0] == PackageManager.PERMISSION_GRANTED
//                val hasPostNotificationsPermission =
//                    grantResults[1] == PackageManager.PERMISSION_GRANTED
//
//                // If the user denied the permissions, show a check.
//                when {
//                    !hasAccessNotificationPolicyPermission || !hasPostNotificationsPermission -> {
//                        getNotificationPermissions()
//                    }
//                    else -> {
//                        Log.d(TAG, "Notification Permissions : Granted successfully")
//                    }
//                }
//            }
//        }
//    }

}