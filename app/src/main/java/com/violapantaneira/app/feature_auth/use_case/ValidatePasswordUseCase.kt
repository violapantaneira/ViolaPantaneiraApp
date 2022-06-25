package com.violapantaneira.app.feature_auth.use_case

import com.violapantaneira.app.R
import com.violapantaneira.app.util.UiText

class ValidatePasswordUseCase : IAuthUseCases.ValidatePassword {
    override fun invoke(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                message = UiText.StringResource(R.string.the_password_cant_be_blank)
            )
        }
        val containsLettersAndDigits =
            password.any { it.isLetter() } && password.any { it.isDigit() }
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                message = UiText.StringResource(R.string.the_password_needs_to_contain_letter_and_digit)
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}