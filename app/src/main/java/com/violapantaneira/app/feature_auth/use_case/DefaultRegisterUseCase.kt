package com.violapantaneira.app.feature_auth.use_case

import com.violapantaneira.app.domain.repository.AuthRepository
import com.violapantaneira.app.feature_auth.domain.repository.DefaultAuthRepository
import com.violapantaneira.app.feature_auth.util.AuthResponse
import javax.inject.Inject

class DefaultRegisterUseCase constructor(
    private val repository: DefaultAuthRepository,
    private val auth: AuthRepository
) : IAuthUseCases.DefaultRegister {

    override fun invoke(
        name: String,
        email: String,
        password: String,
        callback: (AuthResponse) -> Unit
    ) {
        repository.register(email, password) { response ->
            when (response) {
                is AuthResponse.Successful -> {
                    // Update the user name
                    auth.updateName(name)
                }
                is AuthResponse.Error -> TODO()
            }

            callback(response)
        }
    }
}