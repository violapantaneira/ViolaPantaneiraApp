package com.violapantaneira.app.util

sealed class UiEvent {
    data class ShowSnackbar(
        val message: UiText.StringResource,
        val action: UiText.StringResource
    ) : UiEvent()

    object Pop : UiEvent()

    data class Navigate(val route: Route) : UiEvent()
    data class Replace(val route: Route) : UiEvent()
}
