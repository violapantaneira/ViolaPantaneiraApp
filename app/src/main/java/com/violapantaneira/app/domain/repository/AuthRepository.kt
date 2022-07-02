package com.violapantaneira.app.domain.repository

import com.violapantaneira.app.domain.model.User

interface AuthRepository {
    fun getUser(): User?

    fun updateName(name: String)

    fun logOut(): Boolean
}