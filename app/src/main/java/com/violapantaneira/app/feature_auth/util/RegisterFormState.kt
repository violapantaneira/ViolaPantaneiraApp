package com.violapantaneira.app.feature_auth.util

import com.violapantaneira.app.util.UiText

data class RegisterFormState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val error: UiText.StringResource? = null,
    val buttonEnabled: Boolean = false,
)