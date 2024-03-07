package com.peivandian.note_ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.peivandian.base.R
import com.peivandian.base.theme.ColorBlue100
import com.peivandian.base.theme.ColorGray300
import com.peivandian.note_models.NoteEntity

@Composable
fun GridNoteItem(note: NoteEntity, modifier: Modifier, onNoteClick: (NoteEntity) -> Unit) {


    Column(
        modifier = modifier
            .wrapContentWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.spacing_4x))
            )
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.spacing_4x)))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.spacing_5x),
                    start = dimensionResource(id = R.dimen.spacing_5x),
                    end = dimensionResource(id = R.dimen.spacing_5x),
                    bottom = dimensionResource(id = R.dimen.spacing_2x)
                ),
                text = note.title,
                style = MaterialTheme.typography.titleMedium
            )


            OutlinedButton(
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.spacing_4x),
                    start = dimensionResource(id = R.dimen.spacing_4x),
                    end = dimensionResource(id = R.dimen.spacing_4x),
                    bottom = dimensionResource(id = R.dimen.spacing_2x)
                ),
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(dimensionResource(id = R.dimen.spacing_zero))
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(com.peivandian.note_ui.R.drawable.baseline_more_horiz_24),
                    contentDescription = ""
                )
            }

        }
        note.description?.let {
            Text(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_5x)),
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = ColorGray300
            )

        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacing_4x))) {

                Image(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clip(CircleShape)
                        .size(dimensionResource(id = R.dimen.spacing_8x))
                        .background(Color.White),
                    imageVector = ImageVector.vectorResource(com.peivandian.note_ui.R.drawable.baseline_account_circle_24),
                    contentDescription = "",
                    contentScale = ContentScale.Fit
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = dimensionResource(id = R.dimen.spacing_5x))
                        .clip(CircleShape)
                        .size(dimensionResource(id = R.dimen.spacing_8x))
                        .background(Color.White),
                    imageVector = ImageVector.vectorResource(com.peivandian.note_ui.R.drawable.baseline_account_circle_24),
                    contentDescription = "",
                    contentScale = ContentScale.Fit
                )
            }

            Row(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.spacing_4x)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_2x)),
                    imageVector = ImageVector.vectorResource(com.peivandian.note_ui.R.drawable.archive_minus),
                    contentDescription = ""
                )
                Image(
                    modifier = Modifier
                        .background(color = ColorBlue100, shape = CircleShape)
                        .padding(dimensionResource(id = R.dimen.spacing_2x))
                        .clickable { onNoteClick.invoke(note) }
                    ,
                    imageVector = ImageVector.vectorResource(com.peivandian.note_ui.R.drawable.edit_2),
                    contentDescription = ""
                )
            }
        }
    }
}

