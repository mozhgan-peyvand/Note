package com.peivandian.note_ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import com.peivandian.base.R

@Composable
fun NoteItem(modifier: Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.spacing_4x))
            )
            .padding(dimensionResource(id = R.dimen.spacing_4x))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.spacing_4x)))
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.spacing_2x))
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Heli Wbsite Design")
            OutlinedButton(onClick = { /*TODO*/ }) {
                Image(
                    imageVector = ImageVector.vectorResource(com.peivandian.note_ui.R.drawable.baseline_more_horiz_24),
                    contentDescription = ""
                )
            }

        }
        Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                imageVector = ImageVector.vectorResource(com.peivandian.note_ui.R.drawable.baseline_account_circle_24),
                contentDescription = ""
            )
            Row {
                Image(
                    imageVector = ImageVector.vectorResource(com.peivandian.note_ui.R.drawable.baseline_account_circle_24),
                    contentDescription = ""
                )
                Image(
                    imageVector = ImageVector.vectorResource(com.peivandian.note_ui.R.drawable.baseline_account_circle_24),
                    contentDescription = ""
                )
            }
        }
    }
}