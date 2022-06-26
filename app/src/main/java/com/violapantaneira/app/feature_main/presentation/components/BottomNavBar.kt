package com.violapantaneira.app.feature_main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.violapantaneira.app.feature_main.presentation.util.BottomNavItem
import com.violapantaneira.app.ui.theme.Black
import com.violapantaneira.app.ui.theme.Blue
import com.violapantaneira.app.ui.theme.Typography
import com.violapantaneira.app.ui.theme.White
import com.violapantaneira.app.ui.util.color

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backstackEntry = navController.currentBackStackEntryAsState()

    Box(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = White,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 20.dp
                )
        ) {
            items.forEach { item ->
                val selected = item.route == backstackEntry.value?.destination?.route

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .background(
                            color =
                            if (selected)
                                Blue
                            else
                                Color.Transparent,
                            shape = RoundedCornerShape(52.dp)
                        )
                        .padding(
                            horizontal = 16.dp,
                            vertical = 12.dp
                        )
                        .clickable { onItemClick(item) }
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "${item.name} nav",
                        tint =
                        if (selected)
                            Color.White
                        else
                            Black
                    )
                    if (selected)
                        Text(
                            text = item.name,
                            style = Typography.button
                                .color(
                                    if (selected)
                                        Color.White
                                    else
                                        Black
                                )
                        )
                }
            }
        }
    }
}