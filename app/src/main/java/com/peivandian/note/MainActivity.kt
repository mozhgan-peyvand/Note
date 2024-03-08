package com.peivandian.note

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.core.util.Consumer
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.peivandian.base.R
import com.peivandian.base.navigationHelper.AppGraph
import com.peivandian.base.theme.ColorBlue100
import com.peivandian.base.theme.NoteTheme
import com.peivandian.note_ui.screens.HomeViewModel
import com.peivandian.note_ui.util.navigation.NoteRouter
import com.peivandian.note_ui.util.navigation.addNoteGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    lateinit var viewModel: HomeViewModel
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setContent {

            val context = LocalContext.current

            viewModel = hiltViewModel()

            DisposableEffect(Unit) {
                val listener = Consumer<Intent> { intent ->
                    intent.data?.let {
                        viewModel.handleDeeplink(it)
                    }
                }
                addOnNewIntentListener(listener)
                onDispose { removeOnNewIntentListener(listener) }
            }
            val scope = rememberCoroutineScope()

            var hasBottomBar by remember {
                mutableStateOf(true)
            }
            var addNoteClick by remember {
                mutableStateOf(false)
            }
            val snackbarHostState = remember { SnackbarHostState() }

            navHostController = rememberNavController()
            NoteTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(snackbarHostState)
                    },
                    bottomBar = {
                        if (hasBottomBar) {
                            BottomAppBar(
                                actions = {
                                    IconButton(
                                        onClick = {
                                            navHostController.navigate(NoteRouter.SharedNoteScreen.router)
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Share,
                                            contentDescription = "Share contact"
                                        )
                                    }
                                    IconButton(
                                        onClick = {
                                            scope.launch {
                                                snackbarHostState.showSnackbar(
                                                    message = context.getString(R.string.msg_future_feature),
                                                    actionLabel = null
                                                )
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.FavoriteBorder,
                                            contentDescription = "Mark as favorite"
                                        )
                                    }
                                    IconButton(onClick = {
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = context.getString(R.string.msg_future_feature),
                                                actionLabel = null
                                            )
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Email,
                                            contentDescription = "Email contact"
                                        )
                                    }
                                },
                                floatingActionButton = {
                                    FloatingActionButton(
                                        onClick = { addNoteClick = true },
                                        containerColor = ColorBlue100,
                                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.spacing_14x)),
                                        modifier = Modifier.padding(
                                            horizontal = dimensionResource(id = R.dimen.spacing_2x)
                                        ),
                                    ) {
                                        Icon(
                                            modifier = Modifier.padding(
                                                horizontal = dimensionResource(
                                                    id = R.dimen.spacing_2x
                                                )
                                            ),
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "Call contact",
                                            tint = Color.White
                                        )
                                    }
                                }


                            )
                        }
                    }) { paddingValues ->
                    //We need to setup our NavHost in here
                    NoteNavigation(
                        navHostController = navHostController,
                        paddingValues = paddingValues,
                        addNoteClick = addNoteClick,
                        setHasBottomBar = {
                            hasBottomBar = it
                        },
                        setAddNote = { addNoteClick = it },
                        snackbarHostState = snackbarHostState
                    )

                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    fun NoteNavigation(
        navHostController: NavHostController,
        paddingValues: PaddingValues,
        setHasBottomBar: (Boolean) -> Unit,
        addNoteClick: Boolean,
        setAddNote: (Boolean) -> Unit,
        snackbarHostState: SnackbarHostState
    ) {
        NavHost(
            navController = navHostController,
            startDestination = AppGraph.NoteGraph.router,
            modifier = Modifier.padding(paddingValues = paddingValues),
        ) {
            addNoteGraph(
                navController = navHostController,
                setHasBottomBar = setHasBottomBar,
                addNoteClick = addNoteClick,
                setAddNote = setAddNote,
                snackbarHostState = snackbarHostState
            )
        }
    }
}