package com.violapantaneira.app.feature_main.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.violapantaneira.app.feature_main.use_case.MainUseCases
import com.violapantaneira.app.feature_main.util.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    useCases: MainUseCases
) : ViewModel() {

    var isAdmin = mutableStateOf(false)

    var state by mutableStateOf(MainState())

    init {
        useCases.isAdmin {
            isAdmin.value = it
            state = state.copy(loading = false)
        }
    }
}