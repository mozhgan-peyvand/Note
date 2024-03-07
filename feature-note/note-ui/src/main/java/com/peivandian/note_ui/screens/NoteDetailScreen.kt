package com.peivandian.note_ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.peivandian.base.theme.ColorGray100
import com.peivandian.base.theme.ColorGray200
import com.peivandian.base.theme.ColorGray300
import com.peivandian.base.theme.ColorTransparentGray200
import com.peivandian.base.theme.Purple40
import com.peivandian.base.theme.White
import com.peivandian.base.theme.black
import com.peivandian.base.util.UiEvent
import com.peivandian.note_models.NoteItemEvents
import com.peivandian.note_ui.util.ui.ReminderDialog
import java.util.concurrent.TimeUnit
import com.peivandian.note_ui.R as NoteUi
import com.peivandian.base.R as BaseUi

@Composable
fun NoteDetailScreen(
    onPopBackStack: () -> Boolean,
    viewModel: NoteDetailViewModel = hiltViewModel(),
    setHasBottomBar: (Boolean) -> Unit,
) {
    var dialogState by remember { mutableStateOf(false) }

    if (dialogState) {
        ReminderDialog(
            title = viewModel.title,
            onDismiss = { dialogState = false },
            description = viewModel.description
        )
    }

    setHasBottomBar.invoke(false)

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }

    NoteDetailScreen(
        onSaveTodoClick = {
            viewModel.onEvent(NoteItemEvents.OnSaveTodoClick)
        },
        setDialogState = { dialogState = it },
        onTitleChange = { viewModel.onEvent(NoteItemEvents.OnTitleChange(it)) },
        title = viewModel.title,
        description = viewModel.description,
        onDescriptionChange = { viewModel.onEvent(NoteItemEvents.OnDescriptionChange(it)) },
        onNavigationBack = { viewModel.onEvent(NoteItemEvents.OnBackClick) }
    )

}

@Composable
fun NoteDetailScreen(
    onSaveTodoClick: () -> Unit,
    setDialogState: (Boolean) -> Unit,
    onTitleChange: (String) -> Unit,
    title: String,
    description: String,
    onDescriptionChange: (String) -> Unit,
    onNavigationBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = BaseUi.dimen.spacing_4x))
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {

            OutlinedIconButton(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .padding(vertical = dimensionResource(id = BaseUi.dimen.spacing_4x))
                    .size(dimensionResource(id = BaseUi.dimen.spacing_13x)),
                shape = CircleShape,
                onClick = {
                    onNavigationBack.invoke()
                },
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
                    onClick = {
                        onSaveTodoClick.invoke()
                    },
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
                onClick = { setDialogState.invoke(true) },
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
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topEnd = dimensionResource(id = BaseUi.dimen.spacing_6x),
                        topStart = dimensionResource(id = BaseUi.dimen.spacing_6x)
                    )
                )
                .clip(
                    RoundedCornerShape(
                        topEnd = dimensionResource(id = BaseUi.dimen.spacing_6x),
                        topStart = dimensionResource(id = BaseUi.dimen.spacing_6x)
                    )
                )
                .fillMaxSize()
                .padding(
                    start = dimensionResource(id = BaseUi.dimen.spacing_4x),
                    end = dimensionResource(id = BaseUi.dimen.spacing_4x),
                    top = dimensionResource(id = BaseUi.dimen.spacing_4x)
                )

        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = {
                    onTitleChange.invoke(it)
                },
                placeholder = {
                    Text(
                        text = stringResource(id = NoteUi.string.label_title),
                        style = MaterialTheme.typography.titleMedium,
                        color = ColorGray200
                    )
                },
                singleLine = false,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                    focusedIndicatorColor = White,
                    unfocusedIndicatorColor = White,
                )
            )


            TextField(
                modifier = Modifier
                    .background(Color.White),
                value = description,
                onValueChange = {
                    onDescriptionChange(it)
                },
                placeholder = {
                    Text(
                        text = stringResource(id = NoteUi.string.label_description),
                        style = MaterialTheme.typography.titleMedium,
                        color = ColorGray200
                    )
                },
                singleLine = false,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                    focusedIndicatorColor = White,
                    unfocusedIndicatorColor = White,
                )
            )


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

@Composable
fun ShowDialog(
    dialogState: Boolean,
    setDialogState: (Boolean) -> Unit,
    title: String,
    scheduleReminder: (delayMillis: Long, timeSecond: TimeUnit, name: String) -> Unit
) {
    if (dialogState) {
//        ReminderDialog(
//            title = title,
//            onDismiss = {  },
//            scheduleReminder = { delayMillis, timeSecond, name ->
//                scheduleReminder(
//                    delayMillis,
//                    timeSecond,
//                    name
//                )
//            }
//        )
    }
}