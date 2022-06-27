package com.violapantaneira.app.di

import com.violapantaneira.app.domain.repository.AuthRepository
import com.violapantaneira.app.domain.repository.CalendarRepository
import com.violapantaneira.app.domain.repository.DatabaseRepository
import com.violapantaneira.app.feature_main.use_case.GetRollUseCase
import com.violapantaneira.app.feature_main.use_case.IMainUseCases
import com.violapantaneira.app.feature_main.use_case.IsAdminUseCase
import com.violapantaneira.app.feature_main.use_case.MainUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainUseCasesModule {

    @Provides
    @Singleton
    fun provideIsAdminUseCase(
        database: DatabaseRepository,
        auth: AuthRepository
    ): IMainUseCases.IsAdmin =
        IsAdminUseCase(database, auth)

    @Provides
    @Singleton
    fun provideGetRollUseCase(
        database: DatabaseRepository,
        calendar: CalendarRepository
    ): IMainUseCases.GetRoll =
        GetRollUseCase(database, calendar)

    @Provides
    @Singleton
    fun provideMainUseCases(
        isAdmin: IMainUseCases.IsAdmin,
        getRoll: IMainUseCases.GetRoll
    ): MainUseCases = MainUseCases(
        isAdmin,
        getRoll
    )
}