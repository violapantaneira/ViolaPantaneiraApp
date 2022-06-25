package com.violapantaneira.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.violapantaneira.app.feature_auth.presentation.login.LoginScreen
import com.violapantaneira.app.feature_auth.presentation.register.RegisterScreen

fun NavGraphBuilder.setupAuthNavGraph(navController: NavController) {
    navigation(
        startDestination = AuthRoutes.LOGIN,
        route = AuthRoutes()
    ) {
        composable(route = AuthRoutes.LOGIN) {
            LoginScreen(onNavigate = { navController.replace(it.route) })
        }
        composable(route = AuthRoutes.REGISTER) {
            RegisterScreen(onNavigate = {
                navController.replace(
                    it.route
                )
            })
        }
    }
}