package com.violapantaneira.app.feature_auth.use_case

import javax.inject.Inject
import javax.inject.Singleton

data class AuthUseCases @Inject constructor(
    val validateName: IAuthUseCases.ValidateName,
    val validateEmail: IAuthUseCases.ValidateEmail,
    val validatePassword: IAuthUseCases.ValidatePassword,
    val defaultLogin: IAuthUseCases.DefaultLogin,
    val defaultRegister: IAuthUseCases.DefaultRegister
)
