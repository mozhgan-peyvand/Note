package com.peivandian.note_ui.util.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.peivandian.base.R
import com.peivandian.base.theme.ColorBlue100
import com.peivandian.base.theme.ColorGray100
import com.peivandian.base.theme.White
import com.peivandian.note_models.NoteEntity
import com.peivandian.note_ui.screens.GridNoteItem
import com.peivandian.note_ui.screens.NoteItem
import kotlinx.coroutines.launch
import com.peivandian.note_ui.R as NoteUi


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteTabs(
    modifier: Modifier,
    noteList: List<NoteEntity>,
    onNoteClick: (NoteEntity) -> Unit,
) {
    val tabs = enumValues<Tab>()

    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.spacing_4x))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TabRow(
                modifier = Modifier
                    .weight(5f)
                    .height(dimensionResource(id = R.dimen.spacing_15x))
                    .padding(dimensionResource(id = R.dimen.spacing_2x))
                    .clip(RoundedCornerShape(dimensionResource(id = com.peivandian.base.R.dimen.spacing_13x))),
                containerColor = Color.White,
                selectedTabIndex = pagerState.currentPage,
                divider = {},
                indicator = {},
            ) {
                tabs.forEachIndexed { index, item ->
                    if (item.title != Tab.Fab.title)
                        Tab(
                            modifier = Modifier
                                .weight(1f)
                                .height(dimensionResource(id = R.dimen.spacing_8x))
                                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.spacing_13x)))
                                .padding(horizontal = dimensionResource(id = R.dimen.spacing_base))
                                .background(
                                    color = if (index == pagerState.currentPage)
                                        ColorBlue100 else Color.White,
                                    shape = RoundedCornerShape(dimensionResource(id = com.peivandian.base.R.dimen.spacing_9x))
                                ),
                            selected = index == pagerState.currentPage,
                            text = {
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            },
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            selectedContentColor = if (index == pagerState.currentPage)
                                Color.White else Color.Black,
                        )

                }
            }
            Tab(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth()
                    .clip(CircleShape)
                    .background(
                        color = if (3 == pagerState.currentPage)
                            ColorBlue100 else Color.White,
                        shape = CircleShape
                    ),
                selected = 3 == pagerState.currentPage,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(3)
                    }
                },
                selectedContentColor = if (3 == pagerState.currentPage)
                    Color.White else Color.Black,
                icon = {
                    Image(
                        modifier = Modifier
                            .clip(CircleShape),
                        imageVector = ImageVector.vectorResource(NoteUi.drawable.archive_minus),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(
                            color = if (3 == pagerState.currentPage)
                                Color.White else Color.Black
                        )

                    )
                }
            )

        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = true
        ) {
            TabContents(
                tab = tabs[pagerState.currentPage],
                noteList = noteList,
                onNoteClick = onNoteClick,
            )

        }
    }
}

@Composable
fun TabContents(
    tab: Tab,
    noteList: List<NoteEntity>,
    onNoteClick: (NoteEntity) -> Unit,
) {

    when (tab) {
        Tab.All -> {
            AllNoteList(
                noteList = noteList,
                onNoteClick = onNoteClick,
            )
        }

        Tab.Work -> {
            Box(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.spacing_4x))
                    .fillMaxSize()
                    .background(color = White), contentAlignment = Alignment.Center
            ) {
                Text(text = "${tab.title} contents")
            }
        }

        Tab.LifeStyle -> {
            Box(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.spacing_4x))
                    .fillMaxSize()
                    .background(color = White), contentAlignment = Alignment.Center
            ) {
                Text(text = "${tab.title} contents")
            }
        }

        Tab.Fab -> {
            Box(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.spacing_4x))
                    .fillMaxSize()
                    .background(color = White), contentAlignment = Alignment.Center
            ) {
                Text(text = "${tab.title} Fab")
            }
        }
    }
}

enum class Tab(val title: String) {
    All("All"), Work("Work"), LifeStyle("Life Style"), Fab("Fab")
}

@Composable
fun AllNoteList(
    noteList: List<NoteEntity>,
    onNoteClick: (NoteEntity) -> Unit,
) {
    var isGridView by rememberSaveable {
        mutableStateOf(false)
    }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.spacing_4x),
                    vertical = dimensionResource(id = R.dimen.spacing_2x)
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = com.peivandian.note_ui.R.string.label_recent_note))
            Row(modifier = Modifier) {
                LayoutToggleButton(
                    modifier = Modifier,
                    isGridView = isGridView,
                    onToggleClick = {
                        isGridView = !isGridView
                    }
                )
                Divider(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.spacing_3x))
                        .size(
                            height = dimensionResource(id = R.dimen.spacing_5x),
                            width = dimensionResource(id = R.dimen.spacing_base)
                        ),
                    thickness = dimensionResource(id = R.dimen.spacing_half_base),
                    color = ColorGray100
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        imageVector = ImageVector.vectorResource(
                            com.peivandian.note_ui.R.drawable.search_normal
                        ), contentDescription = ""
                    )
                }
            }
        }
        if (isGridView) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                items(noteList) { note ->
                    GridNoteItem(
                        note,
                        Modifier.padding(dimensionResource(id = R.dimen.spacing_2x)),
                        onNoteClick
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(noteList) { note ->
                    NoteItem(
                        note,
                        Modifier.padding(
                            vertical = dimensionResource(id = R.dimen.spacing_2x),
                            horizontal = dimensionResource(
                                id = R.dimen.spacing_4x
                            )
                        ),
                        onNoteClick
                    )
                }
            }
        }
    }
}

@Composable
fun LayoutToggleButton(
    isGridView: Boolean,
    onToggleClick: () -> Unit,
    modifier: Modifier
) {
    val customGridViewImage = painterResource(com.peivandian.note_ui.R.drawable.element_1)
    val customAgendaViewImage = painterResource(com.peivandian.note_ui.R.drawable.row_vertical)

    val imageToShow = if (isGridView) customGridViewImage else customAgendaViewImage

    val tint = LocalContentColor.current

    IconButton(
        onClick = onToggleClick,
        modifier = modifier
    ) {
        Image(
            imageToShow,
            contentDescription = "Toggle Button",
            colorFilter = ColorFilter.tint(tint)
        )
    }
}