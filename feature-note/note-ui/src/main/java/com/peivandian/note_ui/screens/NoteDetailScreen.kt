package com.peivandian.note_ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichText
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import com.peivandian.base.theme.ColorGray100
import com.peivandian.base.theme.ColorGray200
import com.peivandian.base.theme.ColorTransparentGray200
import com.peivandian.base.theme.Purple40
import com.peivandian.base.theme.black
import com.peivandian.note_ui.R as NoteUi
import com.peivandian.base.R as BaseUi

@Composable
fun NoteDetailScreen(
    navController: NavHostController,
    viewModel: NoteViewModel = hiltViewModel(),
    setHasBottomBar: (Boolean) -> Unit
) {
    setHasBottomBar.invoke(false)

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth()) {

            OutlinedIconButton(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .padding(dimensionResource(id = BaseUi.dimen.spacing_4x))
                    .size(dimensionResource(id = BaseUi.dimen.spacing_13x)),
                shape = CircleShape,
                onClick = {},
                border = BorderStroke(
                    dimensionResource(id = BaseUi.dimen.spacing_half_base),
                    color = ColorGray100
                )
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(NoteUi.drawable.arrow_left),
                    contentDescription = ""
                )
            }

            Row(modifier = Modifier.align(Alignment.TopEnd)) {

                OutlinedIconButton(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = BaseUi.dimen.spacing_4x))
                        .size(dimensionResource(id = BaseUi.dimen.spacing_13x)),
                    shape = CircleShape,
                    onClick = {},
                    border = BorderStroke(
                        dimensionResource(id = BaseUi.dimen.spacing_half_base),
                        color = ColorGray100
                    )
                ) {
                    Image(
                        modifier = Modifier,
                        imageVector = ImageVector.vectorResource(NoteUi.drawable.direct_inbox),
                        contentDescription = ""
                    )
                }

                OutlinedIconButton(
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = BaseUi.dimen.spacing_4x),
                            end = dimensionResource(id = BaseUi.dimen.spacing_4x),
                            start = dimensionResource(id = BaseUi.dimen.spacing_2x)
                        )
                        .size(dimensionResource(id = BaseUi.dimen.spacing_13x)),
                    shape = CircleShape,
                    onClick = {},
                    border = BorderStroke(
                        dimensionResource(id = BaseUi.dimen.spacing_half_base),
                        color = ColorGray100
                    )
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(NoteUi.drawable.archive_minus),
                        contentDescription = ""
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.padding(dimensionResource(id = BaseUi.dimen.spacing_4x))) {

                Image(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clip(CircleShape)
                        .size(dimensionResource(id = BaseUi.dimen.spacing_13x))
                        .background(Color.White),
                    imageVector = ImageVector.vectorResource(NoteUi.drawable.baseline_account_circle_24),
                    contentDescription = "",
                    contentScale = ContentScale.Fit
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = dimensionResource(id = BaseUi.dimen.spacing_5x))
                        .clip(CircleShape)
                        .size(dimensionResource(id = BaseUi.dimen.spacing_13x))
                        .background(Color.White),
                    imageVector = ImageVector.vectorResource(NoteUi.drawable.baseline_account_circle_24),
                    contentDescription = "",
                    contentScale = ContentScale.Fit
                )
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(id = BaseUi.dimen.spacing_12x)))
                    .padding(dimensionResource(id = BaseUi.dimen.spacing_4x)),
                colors = ButtonDefaults.buttonColors(Color.White),
                contentPadding = PaddingValues(dimensionResource(id = BaseUi.dimen.spacing_2x))
            ) {
                Image(
                    modifier = Modifier
                        .background(color = Purple40, shape = CircleShape)
                        .padding(dimensionResource(id = BaseUi.dimen.spacing_2x)),
                    imageVector = ImageVector.vectorResource(NoteUi.drawable.flash),
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.padding(horizontal = dimensionResource(id = BaseUi.dimen.spacing_3x)),
                    text = "Work"
                )

            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = dimensionResource(id = BaseUi.dimen.spacing_4x),
                    end = dimensionResource(id = BaseUi.dimen.spacing_4x),
                    top = dimensionResource(id = BaseUi.dimen.spacing_4x)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topEnd = dimensionResource(id = BaseUi.dimen.spacing_6x),
                        topStart = dimensionResource(id = BaseUi.dimen.spacing_6x)
                    )
                )

        ) {
            Text(
                modifier = Modifier.padding(dimensionResource(id = BaseUi.dimen.spacing_4x)),
                text = "Heli Website Design",
                style = MaterialTheme.typography.headlineMedium,
                color = black
            )

            Text(
                modifier = Modifier.padding(dimensionResource(id = BaseUi.dimen.spacing_4x)),
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ",
                style = MaterialTheme.typography.bodyMedium,
                color = ColorGray200
            )
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(dimensionResource(id = BaseUi.dimen.spacing_6x)))
                    .background(black)
                    .padding(dimensionResource(id = BaseUi.dimen.spacing_2x))
                ,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(dimensionResource(id = BaseUi.dimen.spacing_2x)),
                    imageVector = ImageVector.vectorResource(NoteUi.drawable.eraser),
                    contentDescription = ""
                )

                Image(
                    modifier = Modifier
                        .background(color = Color.White, shape = CircleShape)
                        .padding(dimensionResource(id = BaseUi.dimen.spacing_2x)),
                    imageVector = ImageVector.vectorResource(NoteUi.drawable.eraser),
                    contentDescription = ""
                )

                Image(
                    modifier = Modifier
                        .padding(dimensionResource(id = BaseUi.dimen.spacing_2x)),
                    imageVector = ImageVector.vectorResource(NoteUi.drawable.link),
                    contentDescription = ""
                )

                Image(
                    modifier = Modifier
                        .padding(dimensionResource(id = BaseUi.dimen.spacing_2x)),
                    imageVector = ImageVector.vectorResource(NoteUi.drawable.smallcaps),
                    contentDescription = ""
                )

                Image(
                    modifier = Modifier
                        .padding(dimensionResource(id = BaseUi.dimen.spacing_2x)),
                    imageVector = ImageVector.vectorResource(NoteUi.drawable.undo),
                    contentDescription = ""
                )
                Image(
                    modifier = Modifier
                        .background(color = ColorTransparentGray200, shape = CircleShape)
                        .padding(dimensionResource(id = BaseUi.dimen.spacing_2x)),
                    imageVector = ImageVector.vectorResource(NoteUi.drawable.tick_circle),
                    contentDescription = ""
                )
            }


        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(dimensionResource(id = BaseUi.dimen.spacing_6x)))
                .background(black)
                .padding(horizontal = dimensionResource(id = BaseUi.dimen.spacing_8x))
        ) {
            Image(
                modifier = Modifier
                    .padding(dimensionResource(id = BaseUi.dimen.spacing_2x)),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.eraser),
                contentDescription = ""
            )

            Image(
                modifier = Modifier
                    .background(color = Color.White, shape = CircleShape)
                    .padding(dimensionResource(id = BaseUi.dimen.spacing_2x)),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.eraser),
                contentDescription = ""
            )

            Image(
                modifier = Modifier
                    .padding(dimensionResource(id = BaseUi.dimen.spacing_2x)),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.link),
                contentDescription = ""
            )

            Image(
                modifier = Modifier
                    .padding(dimensionResource(id = BaseUi.dimen.spacing_2x)),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.smallcaps),
                contentDescription = ""
            )

            Image(
                modifier = Modifier
                    .padding(dimensionResource(id = BaseUi.dimen.spacing_2x)),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.undo),
                contentDescription = ""
            )
            Image(
                modifier = Modifier
                    .background(color = ColorTransparentGray200, shape = CircleShape)
                    .padding(dimensionResource(id = BaseUi.dimen.spacing_2x)),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.tick_circle),
                contentDescription = ""
            )
        }

    }

}