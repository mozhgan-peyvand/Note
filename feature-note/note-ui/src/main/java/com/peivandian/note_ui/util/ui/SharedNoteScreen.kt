package com.peivandian.note_ui.util.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.peivandian.base.R

@Composable
fun SharedNoteScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.fillMaxSize(),
            text = stringResource(id = R.string.msg_future_feature),
            textAlign = TextAlign.Center
        )
    }
}