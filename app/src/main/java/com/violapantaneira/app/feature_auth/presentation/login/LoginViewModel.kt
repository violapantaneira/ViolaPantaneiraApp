package com.violapantaneira.app.feature_auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.violapantaneira.app.R
import com.violapantaneira.app.feature_auth.use_case.AuthUseCases
import com.violapantaneira.app.feature_auth.util.AuthResponse
import com.violapantaneira.app.feature_auth.util.LoginEvent
import com.violapantaneira.app.feature_auth.util.LoginFormState
import com.violapantaneira.app.navigation.AuthRoutes
import com.violapantaneira.app.navigation.MainRoutes
import com.violapantaneira.app.util.UiEvent
import com.violapantaneira.app.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: AuthUseCases
) : ViewModel() {

    var state by mutableStateOf(LoginFormState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: LoginEvent) =
        when (event) {
            is LoginEvent.EmailChanged -> {
                state = state.copy(email = event.email)
                verifyFields()
            }
            is LoginEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
                verifyFields()
            }
            is LoginEvent.LoginClick -> {
                validateFields().let { isFormValid ->
                    if (isFormValid) {
                        useCases.defaultLogin(
                            email = state.email,
                            password = state.password
                        ) { response ->
                            when (response) {
                                is AuthResponse.Error -> state =
                                    state.copy(
                                        error = UiText.StringResource(
                                            R.string.dynamic,
                                            response.message
                                        ),
                                        buttonEnabled = false
                                    )
                                is AuthResponse.Successful -> sendUiEvent(
                                    UiEvent.Navigate(
                                        MainRoutes()
                                    )
                                )
                            }
                        }
                    }
                }
            }
            is LoginEvent.RegisterNavigateClick -> {
                sendUiEvent(UiEvent.Navigate(AuthRoutes.REGISTER))
            }
        }

    private fun verifyFields() {
        val email = state.email
        val password = state.password

        state = state.copy(
            error = null,
            buttonEnabled = email.isNotBlank() && password.isNotBlank()
        )
    }

    private fun validateFields()
            : Boolean {
        val emailResult = useCases.validateEmail(state.email)
        val passwordResult = useCases.validatePassword(state.password)

        val hasError = listOf(emailResult, passwordResult)
            .any { !it.successful }
        val errorString = emailResult.message ?: passwordResult.message

        state = state.copy(
            error = errorString,
            buttonEnabled = !hasError
        )

        return !hasError
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}