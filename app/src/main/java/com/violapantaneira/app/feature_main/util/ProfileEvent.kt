package com.violapantaneira.app.feature_main.util

sealed class ProfileEvent {
    object ToggleProfileEditingClicked : ProfileEvent()

    object ChangeProfilePhotoClicked : ProfileEvent()

    data class NameChanged(val name: String) : ProfileEvent()

    object SignOutClicked : ProfileEvent()
}
