package com.violapantaneira.app.feature_auth.use_case

import com.violapantaneira.app.feature_auth.domain.repository.DefaultAuthRepository
import com.violapantaneira.app.feature_auth.util.AuthResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultLoginUseCase @Inject constructor(
    private val repository: DefaultAuthRepository
) : IAuthUseCases.DefaultLogin {

    override fun invoke(
        email: String,
        password: String,
        callback: (AuthResponse) -> Unit
    ) = repository.logIn(email, password, callback)
}