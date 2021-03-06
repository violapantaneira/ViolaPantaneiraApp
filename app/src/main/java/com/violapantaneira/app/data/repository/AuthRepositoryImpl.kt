package com.violapantaneira.app.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.violapantaneira.app.domain.model.User
import com.violapantaneira.app.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val auth: FirebaseAuth
) : AuthRepository {

    override fun getUser(): User? {
        val isLoggedIn = getCurrentUser() != null
        if (isLoggedIn)
            return User(
                email = getCurrentUser()?.email,
                name = getCurrentUser()?.displayName,
                photoUrl = getCurrentUser()?.photoUrl
            )

        return null
    }


    override fun updateName(name: String) {
        val updateProfile = userProfileChangeRequest {
            displayName = name
        }
        getCurrentUser()?.updateProfile(updateProfile)
    }

    override fun logOut(): Boolean {
        if (getUser() == null)
            return false

        auth.signOut()
        return true
    }

    private fun getCurrentUser(): FirebaseUser? =
        auth.currentUser
}