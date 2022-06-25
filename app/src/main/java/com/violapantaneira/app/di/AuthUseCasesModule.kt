package com.violapantaneira.app.di

import com.violapantaneira.app.domain.repository.AuthRepository
import com.violapantaneira.app.feature_auth.domain.repository.DefaultAuthRepository
import com.violapantaneira.app.feature_auth.use_case.*
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthUseCasesModule {

    @Provides
    @Singleton
    fun provideValidateNameUseCase(): IAuthUseCases.ValidateName =
        ValidateNameUseCase()

    @Provides
    @Singleton
    fun provideValidateEmailUseCase(): IAuthUseCases.ValidateEmail =
        ValidateEmailUseCase()

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase(): IAuthUseCases.ValidatePassword =
        ValidatePasswordUseCase()

    @Provides
    @Singleton
    fun provideDefaultLoginUseCase(
        repository: DefaultAuthRepository
    ): IAuthUseCases.DefaultLogin =
        DefaultLoginUseCase(repository)

    @Provides
    @Singleton
    fun provideDefaultRegisterUseCase(
        repository: DefaultAuthRepository,
        auth: AuthRepository
    ): IAuthUseCases.DefaultRegister =
        DefaultRegisterUseCase(repository, auth)

    @Provides
    @Singleton
    fun provideAuthUseCases(
        validateName: IAuthUseCases.ValidateName,
        validateEmail: IAuthUseCases.ValidateEmail,
        validatePassword: IAuthUseCases.ValidatePassword,
        defaultLogin: IAuthUseCases.DefaultLogin,
        defaultRegister: IAuthUseCases.DefaultRegister
    ): AuthUseCases =
        AuthUseCases(
            validateName,
            validateEmail,
            validatePassword,
            defaultLogin,
            defaultRegister
        )
}