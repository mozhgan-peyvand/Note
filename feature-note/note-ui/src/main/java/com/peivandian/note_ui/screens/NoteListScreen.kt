package com.peivandian.note_ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.peivandian.base.theme.ColorGray300
import com.peivandian.base.theme.black
import com.peivandian.base.util.UiEvent
import com.peivandian.note_models.NoteEntity
import com.peivandian.note_models.NoteListEvents
import com.peivandian.note_ui.R
import com.peivandian.note_ui.util.ui.NoteTabs
import com.peivandian.note_ui.R as NoteUi
import com.peivandian.base.R as BaseUi

@Composable
fun NoteListScreen(
    navController: NavHostController,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: NoteListViewModel = hiltViewModel(),
    addNoteClick: Boolean,
    setAddNote: (Boolean) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {

    val noteList = viewModel.notes.collectAsState(initial = emptyList()).value
    val event by homeViewModel.event.collectAsState()



    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )

                }

                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    LaunchedEffect(event) {
        when (val currentEvent = event) {
            is Event.NavigateWithDeeplink -> navController.navigate(currentEvent.deeplink)
            Event.None -> Unit
        }

        homeViewModel.consumeEvent()
    }


    NoteListScreen(
        addNoteClick = addNoteClick,
        noteList = noteList,
        onAddNoteClick = {
            viewModel.onEvent(
                NoteListEvents.OnAddNoteClick
            )
        },
        onNoteClick = {
            viewModel.onEvent(
                NoteListEvents.OnNoteClick(it)
            )
        },
        setAddNote = setAddNote,
        searchNoteList = viewModel.searchedList.collectAsState().value,
        onSearchClick = { viewModel.onEvent(NoteListEvents.OnSearchClick(it)) }
    )
}

@Composable
fun OnAddNote(
    addNoteClick: Boolean,
    onAddNoteClick: () -> Unit,
    setAddNote: (Boolean) -> Unit
) {
    if (addNoteClick) {
        onAddNoteClick()
        setAddNote.invoke(false)
    }
}

@Composable
fun NoteListScreen(
    noteList: List<NoteEntity>,
    onAddNoteClick: () -> Unit,
    onNoteClick: (NoteEntity) -> Unit,
    addNoteClick: Boolean,
    setAddNote: (Boolean) -> Unit,
    onSearchClick: (query: String) -> Unit,
    searchNoteList: MutableList<NoteEntity>
) {


    OnAddNote(addNoteClick = addNoteClick, onAddNoteClick = onAddNoteClick, setAddNote = setAddNote)

    Column(modifier = Modifier.fillMaxSize()) {

        Toolbar(modifier = Modifier)

        DateViewPager(modifier = Modifier)

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            NoteTabs(
                Modifier.weight(5f),
                noteList = noteList,
                onNoteClick = { it ->
                    onNoteClick(it)
                },
                onSearchClick = onSearchClick,
                searchNoteList = searchNoteList
            )
        }
    }
}

@Composable
fun Toolbar(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = BaseUi.dimen.spacing_6x),
                vertical = dimensionResource(
                    id = BaseUi.dimen.spacing_6x
                )
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.baseline_account_circle_24),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = BaseUi.dimen.spacing_2x
                    )
                ),
                text = stringResource(id = R.string.profile_name)
            )
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                modifier = Modifier
                    .size(30.dp),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.notification_status),
                contentDescription = ""
            )

            Image(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = com.peivandian.base.R.dimen.spacing_2x))
                    .size(30.dp),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.direct_inbox),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun DateViewPager(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = BaseUi.dimen.spacing_6x))
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = dimensionResource(id = com.peivandian.base.R.dimen.spacing_13x)),
            imageVector = ImageVector.vectorResource(NoteUi.drawable.baseline_arrow_forward_ios_24),
            contentDescription = ""
        )
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                Image(
                    modifier = Modifier.padding(horizontal = dimensionResource(id = com.peivandian.base.R.dimen.spacing_base)),
                    imageVector = ImageVector.vectorResource(NoteUi.drawable.calendar_2),
                    contentDescription = ""
                )
                Text(
                    text = stringResource(id = R.string.label_day),
                    style = MaterialTheme.typography.titleMedium,
                    color = black
                )
            }
            Text(
                text = stringResource(id = R.string.label_date),
                style = MaterialTheme.typography.labelMedium,
                color = ColorGray300
            )

        }

        Image(
            modifier = Modifier
                .padding(start = dimensionResource(id = com.peivandian.base.R.dimen.spacing_13x))
                .align(Alignment.CenterStart),
            imageVector = ImageVector.vectorResource(NoteUi.drawable.baseline_arrow_back_ios_24),
            contentDescription = ""
        )

    }

}

