package com.violapantaneira.app.feature_auth.use_case

import com.violapantaneira.app.feature_auth.util.AuthResponse
import kotlinx.coroutines.flow.Flow

interface IAuthUseCases {

    interface ValidateName {
        operator fun invoke(name: String): ValidationResult
    }

    interface ValidateEmail {
        operator fun invoke(email: String): ValidationResult
    }

    interface ValidatePassword {
        operator fun invoke(password: String): ValidationResult
    }

    interface DefaultLogin {
        operator fun invoke(
            email: String,
            password: String,
            callback: (AuthResponse) -> Unit
        )
    }

    interface DefaultRegister {
        operator fun invoke(
            name: String,
            email: String,
            password: String,
            callback: (AuthResponse) -> Unit
        )
    }
}
