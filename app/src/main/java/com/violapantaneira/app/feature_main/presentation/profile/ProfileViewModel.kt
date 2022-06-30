package com.violapantaneira.app.feature_main.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.violapantaneira.app.domain.repository.AuthRepository
import com.violapantaneira.app.feature_main.util.ProfileEvent
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

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ProfileEvent) =
        when (event) {
            ProfileEvent.SignOutClicked -> auth.signOut { successful ->
                if (successful)
                    sendUiEvent(UiEvent.Navigate(AuthRoutes()))
                else
                    Unit
            }
        }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}