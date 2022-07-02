package com.violapantaneira.app.util

sealed class UiEvent {
    object Pop : UiEvent()

    data class Navigate(val route: Route) : UiEvent()
    data class Replace(val route: Route) : UiEvent()
}
