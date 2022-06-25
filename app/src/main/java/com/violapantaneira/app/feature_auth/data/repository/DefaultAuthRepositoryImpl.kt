package com.violapantaneira.app.feature_auth.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.violapantaneira.app.feature_auth.domain.repository.DefaultAuthRepository
import com.violapantaneira.app.feature_auth.util.AuthResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultAuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : DefaultAuthRepository {

    override fun logIn(
        email: String,
        password: String,
        callback: (AuthResponse) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val currentUser = it.user
                currentUser?.let { user ->
                    callback(AuthResponse.Successful(user))
                }
            }
            .addOnFailureListener {
                val errorMessage = it.message
                errorMessage?.let { message ->
                    callback(AuthResponse.Error(message))
                }
            }
    }


    override fun register(
        email: String,
        password: String,
        callback: (AuthResponse) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val currentUser = it.user
                currentUser?.let { user ->
                    callback(AuthResponse.Successful(user))
                }
            }
            .addOnFailureListener {
                val errorMessage = it.message
                errorMessage?.let { message ->
                    callback(AuthResponse.Error(message))
                }
            }
    }
}