package com.violapantaneira.app.feature_main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.violapantaneira.app.feature_main.presentation.home.HomeScreen
import com.violapantaneira.app.feature_main.presentation.profile.ProfileScreen
import com.violapantaneira.app.feature_main.presentation.search.SearchScreen
import com.violapantaneira.app.navigation.MainRoutes
import com.violapantaneira.app.util.UiEvent

@Composable
fun UserNavigation(
    navController: NavHostController,
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = MainRoutes.HOME
    ) {
        composable(MainRoutes.HOME) { HomeScreen() }
        composable(MainRoutes.SEARCH) { SearchScreen() }
        composable(MainRoutes.PROFILE) { ProfileScreen(onNavigate = onNavigate) }
    }
}
