package com.violapantaneira.app.feature_auth.util

sealed class RegisterEvent {
    data class NameChanged(val name: String) : RegisterEvent()
    data class EmailChanged(val email: String) : RegisterEvent()
    data class PasswordChanged(val password: String) : RegisterEvent()

    object RegisterClick : RegisterEvent()
    object LoginNavigateClick : RegisterEvent()
}
