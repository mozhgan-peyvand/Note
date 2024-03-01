package com.peivandian.note_ui.util.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import com.peivandian.base.R
import com.peivandian.note_ui.screens.NoteItem
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsWithHorizontalPagerScreen(modifier: Modifier) {
    val tabs = enumValues<Tab>()

    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(dimensionResource(id = R.dimen.spacing_13x))
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {


            TabRow(
                modifier = Modifier
                    .weight(5f)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(dimensionResource(id = com.peivandian.base.R.dimen.spacing_9x))
                    )
                    .clip(
                        RoundedCornerShape(dimensionResource(id = com.peivandian.base.R.dimen.spacing_9x))
                    )
                    .wrapContentWidth()
                    .padding(dimensionResource(id = com.peivandian.base.R.dimen.spacing_2x)),

                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onBackground,
                selectedTabIndex = pagerState.currentPage,
                divider = {},
                indicator = {}
            ) {
                tabs.forEachIndexed { index, item ->
                    Tab(
                        modifier = Modifier
                            .background(
                                color = if (index == pagerState.currentPage)
                                    Color.Blue else Color.White,
                                shape = RoundedCornerShape(dimensionResource(id = com.peivandian.base.R.dimen.spacing_9x))
                            )

                            .wrapContentWidth()
                            .clip(RoundedCornerShape(dimensionResource(id = com.peivandian.base.R.dimen.spacing_9x))),
                        selected = index == pagerState.currentPage,
                        text = { Text(text = item.title) },
                        onClick = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(index)
                            }
                        }
                    )
                }
            }

            Button(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.spacing_2x))
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = CircleShape
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(com.peivandian.note_ui.R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = "",
                    modifier = Modifier
                        .background(color = Color.White)
                        .clip(CircleShape),
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = true
        ) {
            TabContents(tab = tabs[pagerState.currentPage])
        }
    }
}

@Composable
fun TabContents(tab: Tab) {
    AllNoteList()
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = Color.Green), contentAlignment = Alignment.Center
//    ) {
//        Text(text = "${tab.title} contents")
//    }
}

enum class Tab(val title: String) {
    TAB_1("All"), TAB_2("Work"), TAB_3("Life Style")
}

@Composable
fun AllNoteList() {
    Column {
        LazyColumn (modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.spacing_2x))){
            items(5) {
                NoteItem(Modifier.padding(dimensionResource(id = R.dimen.spacing_2x)))
            }
        }
    }
}