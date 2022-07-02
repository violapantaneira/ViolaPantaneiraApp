package com.violapantaneira.app.feature_main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.violapantaneira.app.feature_main.presentation.home.HomeScreen
import com.violapantaneira.app.feature_main.presentation.profile.ProfileScreen
import com.violapantaneira.app.feature_main.presentation.roll.RollScreen
import com.violapantaneira.app.feature_main.presentation.search.SearchScreen
import com.violapantaneira.app.navigation.MainRoutes
import com.violapantaneira.app.util.UiEvent

@Composable
fun MainNavigation(
    navController: NavHostController,
    onNavigate: (UiEvent.Navigate) -> Unit,
    onReplace: (UiEvent.Replace) -> Unit,
    showSnackbar: (UiEvent.ShowSnackbar) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = MainRoutes.HOME
    ) {
        composable(MainRoutes.HOME) {
            HomeScreen(
                onNavigate = onNavigate,
                showSnackbar = showSnackbar
            )
        }
        composable(MainRoutes.SEARCH) { SearchScreen(onNavigate) }
        composable(MainRoutes.ROLL) { RollScreen() }
        composable(MainRoutes.PROFILE) {
            ProfileScreen(
                onReplace = onReplace,
                showSnackbar = showSnackbar
            )
        }
    }
}
