package com.violapantaneira.app.domain.repository

import com.violapantaneira.app.domain.model.User
import com.violapantaneira.app.feature_auth.util.AuthResponse

interface AuthRepository {
    fun getUser(): User?

    fun updateName(name: String)

    fun signOut(callback: (succesful: Boolean) -> Unit)
}