package com.violapantaneira.app.di

import com.violapantaneira.app.data.repository.AuthRepositoryImpl
<<<<<<< Updated upstream
import com.violapantaneira.app.domain.repository.AuthRepository
=======
import com.violapantaneira.app.data.repository.CalendarRepositoryImpl
import com.violapantaneira.app.data.repository.DatabaseRepositoryImpl
import com.violapantaneira.app.domain.repository.AuthRepository
import com.violapantaneira.app.domain.repository.CalendarRepository
import com.violapantaneira.app.domain.repository.DatabaseRepository
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    abstract fun bindAuthRepository(
        authRepository: AuthRepositoryImpl
    ): AuthRepository
=======
    fun provideAuthRepository(
        auth: FirebaseAuth
    ): AuthRepository =
        AuthRepositoryImpl(auth)

    @Provides
    @Singleton
    fun provideDatabaseRepository(
        database: FirebaseFirestore,
        calendar: CalendarRepository
    ): DatabaseRepository =
        DatabaseRepositoryImpl(database, calendar)

    @Provides
    @Singleton
    fun provideCalendarRepository(): CalendarRepository =
        CalendarRepositoryImpl()
>>>>>>> Stashed changes
}