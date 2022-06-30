package com.violapantaneira.app.feature_main.presentation

import androidx.compose.runtime.Composable
<<<<<<< Updated upstream

@Composable
fun MainScreen() {

=======
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.violapantaneira.app.util.UiEvent

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val navController = rememberNavController()

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
                    .padding(horizontal = 24.dp)
            )
        }
    ) { padding ->
        if (isAdmin)
            AdminNavigation(
                navController,
                onNavigate
            )
        else
            UserNavigation(
                navController,
                onNavigate
            )

        Log.i("Padding", padding.toString())
    }
>>>>>>> Stashed changes
}