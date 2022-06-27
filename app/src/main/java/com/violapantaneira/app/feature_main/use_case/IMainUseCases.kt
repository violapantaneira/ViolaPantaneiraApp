package com.violapantaneira.app.feature_main.use_case

import com.violapantaneira.app.domain.model.Roll

interface IMainUseCases {
    interface IsAdmin {
        operator fun invoke(callback: (Boolean) -> Unit)
    }

    interface GetRoll {
        operator fun invoke(callback: (Roll) -> Unit)
    }
}