package com.peivandian.note_ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.SnackbarHostState
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
import com.peivandian.base.theme.Purple40
import com.peivandian.base.theme.White
import com.peivandian.base.util.UiEvent
import com.peivandian.note_models.NoteItemEvents
import com.peivandian.note_ui.util.CheckPermission
import com.peivandian.note_ui.util.checkForLocationPermission
import com.peivandian.note_ui.util.ui.ReminderDialog
import com.peivandian.note_ui.R as NoteUi
import com.peivandian.base.R as BaseUi

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NoteDetailScreen(
    onPopBackStack: () -> Boolean,
    viewModel: NoteDetailViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
) {


    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }

                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }


    NoteDetailScreen(
        onSaveTodoClick = {
            viewModel.onEvent(NoteItemEvents.OnSaveTodoClick)
        },
        onTitleChange = { viewModel.onEvent(NoteItemEvents.OnTitleChange(it)) },
        title = viewModel.title,
        description = viewModel.description,
        onDescriptionChange = { viewModel.onEvent(NoteItemEvents.OnDescriptionChange(it)) },
        onNavigationBack = { viewModel.onEvent(NoteItemEvents.OnBackClick) },
    )

}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NoteDetailScreen(
    onSaveTodoClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    title: String,
    description: String,
    onDescriptionChange: (String) -> Unit,
    onNavigationBack: () -> Unit,
) {
    val context = LocalContext.current

    var dialogState by remember { mutableStateOf(false) }

    var showPermission by remember {
        mutableStateOf(false)
    }
    var hasLocationPermission by remember {
        mutableStateOf(checkForLocationPermission(context))
    }
    if (dialogState) {
        ReminderDialog(
            title = title,
            onDismiss = { dialogState = false },
            description = description
        )
    }
    if (showPermission) {
        CheckPermission(
            setDialogState = { dialogState = it },
            setHasLocationPermission = { hasLocationPermission = it }
        )
    }

    NoteDetailScreen(
        onSaveTodoClick = onSaveTodoClick,
        setDialogState = { dialogState = it },
        onTitleChange = onTitleChange,
        title = title,
        description =description,
        onDescriptionChange = onDescriptionChange ,
        onNavigationBack = onNavigationBack,
        hasLocationPermission = hasLocationPermission,
        setShowPermission = { showPermission = it }
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
    onNavigationBack: () -> Unit,
    setShowPermission: (Boolean) -> Unit,
    hasLocationPermission: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = dimensionResource(id = BaseUi.dimen.spacing_2x),
                bottom = dimensionResource(id = BaseUi.dimen.spacing_4x),
                end = dimensionResource(id = BaseUi.dimen.spacing_2x),
                start = dimensionResource(id = BaseUi.dimen.spacing_2x)
            )
    ) {

        ToolbarNoteDetail(
            modifier = Modifier.weight(1f),
            onSaveTodoClick = onSaveTodoClick,
            onNavigationBack = onNavigationBack
        )

        ScheduleNotes(
            modifier = Modifier.weight(1f),
            setDialogState = setDialogState,
            setShowPermission = setShowPermission,
            hasLocationPermission = hasLocationPermission
        )

        ContentNote(
            modifier = Modifier.weight(7f), onTitleChange = onTitleChange,
            title = title,
            description = description,
            onDescriptionChange = onDescriptionChange
        )

    }
}

@Composable
fun ToolbarNoteDetail(
    modifier: Modifier,
    onNavigationBack: () -> Unit,
    onSaveTodoClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedIconButton(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .padding(
                    top = dimensionResource(id = BaseUi.dimen.spacing_2x),
                    start = dimensionResource(id = BaseUi.dimen.spacing_4x)
                )
                .size(dimensionResource(id = BaseUi.dimen.spacing_11x)),
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
                    .padding(top = dimensionResource(id = BaseUi.dimen.spacing_2x))
                    .size(dimensionResource(id = BaseUi.dimen.spacing_11x)),
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
                        top = dimensionResource(id = BaseUi.dimen.spacing_2x),
                        end = dimensionResource(id = BaseUi.dimen.spacing_4x),
                        start = dimensionResource(id = BaseUi.dimen.spacing_2x)
                    )
                    .size(dimensionResource(id = BaseUi.dimen.spacing_11x)),
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
}

@Composable
fun ScheduleNotes(
    modifier: Modifier,
    setDialogState: (Boolean) -> Unit,
    setShowPermission: (Boolean) -> Unit,
    hasLocationPermission: Boolean
) {
    Row(
        modifier = modifier
            .padding(horizontal = dimensionResource(id = BaseUi.dimen.spacing_4x))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.padding(
                vertical = dimensionResource(id = BaseUi.dimen.spacing_2x)
            )
        ) {

            Image(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clip(CircleShape)
                    .background(Color.White)
                    .size(dimensionResource(id = BaseUi.dimen.spacing_11x)),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.baseline_account_circle_24),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Image(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = dimensionResource(id = BaseUi.dimen.spacing_5x))
                    .clip(CircleShape)
                    .size(dimensionResource(id = BaseUi.dimen.spacing_11x))
                    .background(Color.White),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.baseline_account_circle_24),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }

        Button(
            onClick = {
                if (hasLocationPermission) {
                    setDialogState.invoke(true)
                } else {
                    setShowPermission.invoke(true)
                }
            },
            modifier = Modifier
                .padding(dimensionResource(id = BaseUi.dimen.spacing_2x))
                .clip(RoundedCornerShape(dimensionResource(id = BaseUi.dimen.spacing_12x))),
            colors = ButtonDefaults.buttonColors(Color.White),
            contentPadding = PaddingValues(dimensionResource(id = BaseUi.dimen.spacing_base))
        ) {
            Image(
                modifier = Modifier
                    .background(color = Purple40, shape = CircleShape)
                    .padding(dimensionResource(id = BaseUi.dimen.spacing_2x)),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.flash),
                contentDescription = "",
                contentScale = ContentScale.Inside
            )
            Text(
                modifier = Modifier.padding(horizontal = dimensionResource(id = BaseUi.dimen.spacing_2x)),
                text = "Work"
            )

        }
    }
}

@Composable
fun ContentNote(
    modifier: Modifier,
    onTitleChange: (String) -> Unit,
    title: String,
    description: String,
    onDescriptionChange: (String) -> Unit,
) {
    Column(
        modifier = modifier
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
                .fillMaxHeight()
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
}