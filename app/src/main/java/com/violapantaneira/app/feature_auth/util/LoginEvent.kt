package com.violapantaneira.app.feature_auth.util

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()

    object LoginClick : LoginEvent()
    object RegisterNavigateClick : LoginEvent()
}
