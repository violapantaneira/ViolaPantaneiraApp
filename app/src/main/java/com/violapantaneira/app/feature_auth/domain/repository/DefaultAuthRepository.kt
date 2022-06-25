package com.violapantaneira.app.feature_auth.domain.repository

import com.violapantaneira.app.feature_auth.util.AuthResponse
import kotlinx.coroutines.flow.Flow

interface DefaultAuthRepository {

    fun logIn(
        email: String,
        password: String,
        callback: (AuthResponse) -> Unit
    )

    fun register(
        email: String,
        password: String,
        callback: (AuthResponse) -> Unit
    )
}