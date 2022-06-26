package com.violapantaneira.app.feature_main.use_case

import com.violapantaneira.app.domain.repository.AuthRepository
import com.violapantaneira.app.domain.repository.DatabaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IsAdminUseCase @Inject constructor(
    private val database: DatabaseRepository,
    val auth: AuthRepository
) : IMainUseCases.IsAdmin {

    override fun invoke(callback: (Boolean) -> Unit) {
        database.getAdmins { admins ->
            val user = auth.getUser()

            val isAdmin = admins?.any { it.email == user?.email }
            callback(isAdmin ?: false)
        }
    }
}