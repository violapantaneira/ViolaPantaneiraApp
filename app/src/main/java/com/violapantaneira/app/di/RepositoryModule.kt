package com.violapantaneira.app.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.violapantaneira.app.data.repository.AuthRepositoryImpl
import com.violapantaneira.app.data.repository.CalendarRepositoryImpl
import com.violapantaneira.app.data.repository.DatabaseRepositoryImpl
import com.violapantaneira.app.domain.repository.AuthRepository
import com.violapantaneira.app.domain.repository.CalendarRepository
import com.violapantaneira.app.domain.repository.DatabaseRepository
import com.violapantaneira.app.feature_auth.data.repository.DefaultAuthRepositoryImpl
import com.violapantaneira.app.feature_auth.domain.repository.DefaultAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDefaultAuthRepository(
        auth: FirebaseAuth
    ): DefaultAuthRepository =
        DefaultAuthRepositoryImpl(auth)

    @Provides
    @Singleton
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
}