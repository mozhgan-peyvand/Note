package com.peivandian.note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.peivandian.base.navigationHelper.AppGraph
import com.peivandian.note.ui.theme.NoteTheme
import com.peivandian.note_ui.util.navigation.addNoteGraph

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navHostController = rememberNavController()
            NoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NoteNavigation(navHostController = navHostController)

                }
            }
        }
    }
}

@Composable
fun NoteNavigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = AppGraph.NoteGraph.router) {
        addNoteGraph(navHostController)
    }
}