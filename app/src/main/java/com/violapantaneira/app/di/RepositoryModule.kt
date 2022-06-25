package com.violapantaneira.app.di

import com.violapantaneira.app.data.repository.AuthRepositoryImpl
import com.violapantaneira.app.domain.repository.AuthRepository
import com.violapantaneira.app.feature_auth.data.repository.DefaultAuthRepositoryImpl
import com.violapantaneira.app.feature_auth.domain.repository.DefaultAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDefaultAuthRepository(
        defaultAuthRepository: DefaultAuthRepositoryImpl
    ): DefaultAuthRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepository: AuthRepositoryImpl
    ): AuthRepository
}