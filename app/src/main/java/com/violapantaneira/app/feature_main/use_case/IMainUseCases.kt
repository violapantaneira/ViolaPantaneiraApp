package com.violapantaneira.app.feature_main.use_case

interface IMainUseCases {
    interface IsAdmin {
        operator fun invoke(callback: (Boolean) -> Unit)
    }
}