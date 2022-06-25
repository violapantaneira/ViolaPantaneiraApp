package com.violapantaneira.app.feature_auth.use_case

import android.util.Patterns
import com.violapantaneira.app.R
import com.violapantaneira.app.util.UiText

class ValidateEmailUseCase : IAuthUseCases.ValidateEmail{
    override fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                message = UiText.StringResource(R.string.the_email_cant_be_blank)
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                message = UiText.StringResource(R.string.thats_not_a_valid_email)
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}