package com.peivandian.note_ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.peivandian.note_ui.util.navigation.navigateToNoteDetailScreen

@Composable
fun NoteListScreen(
    navHostController: NavHostController,
    viewModel: NoteViewModel = hiltViewModel()
    ) {

    Text(text = "hi im note list")
    Button(onClick = { navHostController.navigateToNoteDetailScreen() }) {
        Text(text = "next page")
    }
}