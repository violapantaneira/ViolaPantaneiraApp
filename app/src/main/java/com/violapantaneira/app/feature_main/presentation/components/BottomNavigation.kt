package com.violapantaneira.app.feature_main.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.violapantaneira.app.feature_main.navigation.MainTab
import com.violapantaneira.app.navigation.replace
import com.violapantaneira.app.ui.theme.Black
import com.violapantaneira.app.ui.theme.Blue
import com.violapantaneira.app.ui.theme.White

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    isAdmin: Boolean = false,
    tabs: List<MainTab>,
    navController: NavController
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    Surface(
        modifier = modifier
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp
            ),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = White,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 20.dp
                )
        ) {
            for (tab in tabs) {

                val selected = tab.route == currentDestination?.route

                val addTab: @Composable () -> Unit = {
                    BottomNavigationItem(
                        modifier = Modifier
                            .weight(1f)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(bounded = false)
                            ) {
                                navController.replace(tab.route)
                            },
                        tab = tab,
                        enabled = selected
                    )
                }

                if (!isAdmin)
                    when (tab) {
                        !is MainTab.Roll -> {
                            addTab()
                        }
                        else -> Unit
                    }
                else
                    addTab()

            }
        }
    }
}

@Composable
private fun BottomNavigationItem(
    modifier: Modifier = Modifier,
    tab: MainTab,
    enabled: Boolean = false,
) {

    val backgroundColor = if (enabled) Blue else Color.Transparent
    val contentColor = if (enabled) Color.White else Black

    val animatedScale by animateFloatAsState(
        targetValue = if (enabled) 1f else .8f,
        animationSpec = TweenSpec(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )

    Surface(
        modifier = modifier,
        color = Color.Transparent
    ) {
        val icon = painterResource(tab.icon)
        val label = stringResource(tab.label)

        Icon(
            modifier = Modifier
                .fillMaxWidth()
                .scale(animatedScale)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(50.dp)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ),
            painter = icon,
            contentDescription = "$label icon",
            tint = contentColor
        )
    }
}
