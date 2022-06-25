package com.violapantaneira.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.violapantaneira.app.domain.repository.AuthRepository
import com.violapantaneira.app.feature_main.presentation.MainScreen
import com.violapantaneira.app.navigation.AuthRoutes
import com.violapantaneira.app.navigation.MainRoutes
import com.violapantaneira.app.navigation.setupAuthNavGraph
import com.violapantaneira.app.ui.theme.ViolaPantaneiraTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var auth: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViolaPantaneiraTheme {
                // Set the status bar color
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setSystemBarsColor(Color.White)
                }

                val startRoute =
                    if (auth.getUser() == null) AuthRoutes() else MainRoutes()

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = startRoute
                ) {
                    setupAuthNavGraph(navController)

                    composable(route = MainRoutes()) { MainScreen() }
                }
            }
        }
    }
}
