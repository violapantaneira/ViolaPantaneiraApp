package com.violapantaneira.app.feature_main.use_case

import javax.inject.Inject

data class MainUseCases @Inject constructor(
    val isAdmin: IMainUseCases.IsAdmin,
    val getRoll: IMainUseCases.GetRoll
)
