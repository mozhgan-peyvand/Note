package com.peivandian.note_ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun NoteDetailScreen(
    navController: NavHostController,
    viewModel: NoteViewModel = hiltViewModel()
) {
    Text(text = "hi im note detail screen")
}