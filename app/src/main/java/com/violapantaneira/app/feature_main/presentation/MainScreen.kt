package com.violapantaneira.app.feature_main.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.violapantaneira.app.feature_main.navigation.MainNavigation
import com.violapantaneira.app.feature_main.navigation.MainTab
import com.violapantaneira.app.feature_main.presentation.components.BottomNavigation
import com.violapantaneira.app.ui.theme.Blue
import com.violapantaneira.app.ui.util.DefaultSnackbar
import com.violapantaneira.app.util.UiEvent
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    onReplace: (UiEvent.Replace) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val isAdmin by remember { viewModel.isAdmin }

    val tabs = listOf(
        MainTab.Home,
        MainTab.Search,
        MainTab.Roll,
        MainTab.Profile,
    )

    when (viewModel.state.loading) {
        false -> Scaffold(
            scaffoldState = scaffoldState,
            snackbarHost = {
                scaffoldState.snackbarHostState
            },
            bottomBar = {
                BottomNavigation(
                    isAdmin = isAdmin,
                    tabs = tabs,
                    navController = navController
                )
            }
        ) { padding ->

            val context = LocalContext.current
            val showSnackbar: (UiEvent.ShowSnackbar) -> Unit = { event ->
                scope.launch {
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context),
                        actionLabel = event.action.asString(context),
                        duration = SnackbarDuration.Short
                    )
                }
            }

            MainNavigation(
                navController,
                onNavigate,
                onReplace,
                showSnackbar
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                DefaultSnackbar(
                    snackbarHostState = scaffoldState.snackbarHostState,
                    onDismiss = {
                        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                    },
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
        else -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Blue
            )
        }
    }
}
