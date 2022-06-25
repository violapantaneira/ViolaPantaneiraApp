package com.violapantaneira.app.feature_auth.use_case

import com.violapantaneira.app.R
import com.violapantaneira.app.util.UiText

class ValidateNameUseCase : IAuthUseCases.ValidateName {
    override fun invoke(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(
                successful = false,
                message = UiText.StringResource(R.string.the_name_cant_be_blank)
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}