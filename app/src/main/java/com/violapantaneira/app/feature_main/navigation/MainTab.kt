package com.violapantaneira.app.feature_main.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.violapantaneira.app.R
import com.violapantaneira.app.navigation.MainRoutes
import com.violapantaneira.app.util.Route

sealed class MainTab(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val route: Route
) {

    object Home: MainTab(
        label = R.string.home,
        icon = R.drawable.ic_home,
        route = MainRoutes.HOME
    )

    object Search: MainTab(
        label = R.string.search,
        icon = R.drawable.ic_search,
        route = MainRoutes.SEARCH
    )

    object Roll: MainTab(
        label = R.string.roll,
        icon = R.drawable.ic_list,
        route = MainRoutes.ROLL
    )

    object Profile: MainTab(
        label = R.string.profile,
        icon = R.drawable.ic_user,
        route = MainRoutes.PROFILE
    )
}
