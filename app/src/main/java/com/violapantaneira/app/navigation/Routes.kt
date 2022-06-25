package com.violapantaneira.app.navigation

import com.violapantaneira.app.util.Route

object AuthRoutes {
    operator fun invoke() : Route = "auth"

    const val LOGIN: Route = "login"
    const val REGISTER: Route = "register"
}

object MainRoutes {
    operator fun invoke() : Route = "main"

    const val HOME: Route = "home"
    const val SEARCH: Route = "search"
    const val PROFILE: Route = "profile"
}