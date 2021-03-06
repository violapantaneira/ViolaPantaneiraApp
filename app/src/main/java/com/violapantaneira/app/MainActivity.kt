package com.violapantaneira.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.violapantaneira.app.domain.repository.AuthRepository
import com.violapantaneira.app.feature_main.presentation.MainScreen
import com.violapantaneira.app.feature_song.presentation.SongScreen
import com.violapantaneira.app.navigation.*
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
                    systemUiController.setSystemBarsColor(Color.White.copy(alpha = .6f))
                }

                val startRoute =
                    if (auth.getUser() == null) AuthRoutes() else MainRoutes()

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = startRoute
                ) {
                    setupAuthNavGraph(navController)

                    composable(route = MainRoutes()) {
                        MainScreen(
                            onNavigate = { navController.navigate(it.route) },
                            onReplace = { navController.replace(it.route) }
                        )
                    }

                    composable(
                        route = "${SongRoutes()}/{songId}",
                        arguments = listOf(
                            navArgument("songId") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        SongScreen(
                            onPop = { navController.popBackStack() },
                            songId = it.arguments?.getString("songId")!!
                        )
                    }
                }
            }
        }
    }
}
