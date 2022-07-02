package com.violapantaneira.app.feature_main.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.violapantaneira.app.domain.repository.AuthRepository
import com.violapantaneira.app.feature_main.util.ProfileEvent
import com.violapantaneira.app.feature_main.util.ProfileState
import com.violapantaneira.app.navigation.AuthRoutes
import com.violapantaneira.app.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val auth: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(ProfileState())

    val user = auth.getUser()

    init {
        state = state.copy(name = user?.name ?: "")
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.ToggleProfileEditingClicked -> {
                if (state.editing)
                    auth.updateName(state.name)
                state = state.copy(editing = !state.editing)
            }
            is ProfileEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }
            is ProfileEvent.ChangeProfilePhotoClicked -> {}
            is ProfileEvent.SignOutClicked -> {
                auth.logOut().let { successful ->
                    if (successful)
                        sendUiEvent(UiEvent.Replace(AuthRoutes()))
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) =
        viewModelScope.launch {
            _uiEvent.send(event)
        }
}