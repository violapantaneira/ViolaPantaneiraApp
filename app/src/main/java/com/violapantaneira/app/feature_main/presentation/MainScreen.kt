package com.violapantaneira.app.feature_main.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.violapantaneira.app.R
import com.violapantaneira.app.feature_main.navigation.AdminNavigation
import com.violapantaneira.app.feature_main.navigation.UserNavigation
import com.violapantaneira.app.feature_main.presentation.components.BottomNavBar
import com.violapantaneira.app.feature_main.presentation.util.BottomNavItem
import com.violapantaneira.app.navigation.MainRoutes
import com.violapantaneira.app.navigation.replace
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

    val adminItems = listOf(
        BottomNavItem(
            name = stringResource(R.string.home),
            route = MainRoutes.HOME,
            icon = ImageVector.vectorResource(id = R.drawable.ic_home)
        ),
        BottomNavItem(
            name = stringResource(R.string.search),
            route = MainRoutes.SEARCH,
            icon = ImageVector.vectorResource(id = R.drawable.ic_search)
        ),
        BottomNavItem(
            name = stringResource(R.string.roll),
            route = MainRoutes.ROLL,
            icon = ImageVector.vectorResource(id = R.drawable.ic_list)
        ),
        BottomNavItem(
            name = stringResource(R.string.profile),
            route = MainRoutes.PROFILE,
            icon = ImageVector.vectorResource(id = R.drawable.ic_user)
        )
    )
    val userItems = listOf(
        BottomNavItem(
            name = stringResource(R.string.home),
            route = MainRoutes.HOME,
            icon = ImageVector.vectorResource(id = R.drawable.ic_home)
        ),
        BottomNavItem(
            name = stringResource(R.string.search),
            route = MainRoutes.SEARCH,
            icon = ImageVector.vectorResource(id = R.drawable.ic_search)
        ),
        BottomNavItem(
            name = stringResource(R.string.profile),
            route = MainRoutes.PROFILE,
            icon = ImageVector.vectorResource(id = R.drawable.ic_user)
        )
    )

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            scaffoldState.snackbarHostState
        },
        bottomBar = {
            BottomNavBar(
                items =
                if (isAdmin)
                    adminItems
                else
                    userItems,
                navController = navController,
                onItemClick = {
                    navController.replace(it.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp,
                        vertical = 16.dp
                    )
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

        if (isAdmin)
            AdminNavigation(
                navController,
                onNavigate,
                onReplace,
                showSnackbar
            )
        else
            UserNavigation(
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
}
