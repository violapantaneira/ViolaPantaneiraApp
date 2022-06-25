package com.violapantaneira.app.feature_auth.use_case

import com.violapantaneira.app.util.UiText

data class ValidationResult(
    val successful: Boolean,
    val message: UiText.StringResource? = null
)