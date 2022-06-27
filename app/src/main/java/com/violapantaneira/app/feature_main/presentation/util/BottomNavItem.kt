package com.violapantaneira.app.feature_main.presentation.util

import androidx.compose.ui.graphics.vector.ImageVector
import com.violapantaneira.app.util.Route

data class BottomNavItem(
    val name: String,
    val route: Route,
    val icon: ImageVector,
)
