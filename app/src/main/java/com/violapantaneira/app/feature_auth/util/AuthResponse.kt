package com.violapantaneira.app.feature_auth.util

import com.google.firebase.auth.FirebaseUser

sealed class AuthResponse {
    data class Successful(val user: FirebaseUser) : AuthResponse()
    data class Error(val message: String) : AuthResponse()
}
