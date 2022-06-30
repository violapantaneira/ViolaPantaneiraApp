package com.violapantaneira.app.feature_main.util

sealed class ProfileEvent {
    object SignOutClicked: ProfileEvent()
}
