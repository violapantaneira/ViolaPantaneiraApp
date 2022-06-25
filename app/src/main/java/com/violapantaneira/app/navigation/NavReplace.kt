package com.violapantaneira.app.navigation

import androidx.navigation.NavController
import com.violapantaneira.app.util.Route

fun NavController.replace(route: Route) {
    popBackStack(graph.startDestinationId, true)
    graph.setStartDestination(route)
    navigate(route)
}
