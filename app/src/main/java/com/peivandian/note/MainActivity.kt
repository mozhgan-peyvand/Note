package com.peivandian.note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.peivandian.base.navigationHelper.AppGraph
import com.peivandian.base.util.BottomNavigationItem
import com.peivandian.note.ui.theme.NoteTheme
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
                                FloatingActionButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Call contact"
                                    )
                                }
                            }


                        )
                    }) { paddingValues ->
                    //We need to setup our NavHost in here
                    NoteNavigation(navHostController = navHostController, paddingValues)

                }
            }
        }
    }
}

@Composable
fun NoteNavigation(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = AppGraph.NoteGraph.router,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        addNoteGraph(navHostController)
    }
}