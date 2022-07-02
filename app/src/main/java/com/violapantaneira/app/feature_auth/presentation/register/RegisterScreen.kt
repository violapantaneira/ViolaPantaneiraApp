package com.violapantaneira.app.feature_auth.presentation.register

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.violapantaneira.app.R
import com.violapantaneira.app.feature_auth.util.RegisterEvent
import com.violapantaneira.app.ui.components.FormButton
import com.violapantaneira.app.ui.components.FormField
import com.violapantaneira.app.ui.theme.Blue
import com.violapantaneira.app.ui.theme.Typography
import com.violapantaneira.app.ui.util.*
import com.violapantaneira.app.util.TextFieldType
import com.violapantaneira.app.util.UiEvent

@Composable
fun RegisterScreen(
    onReplace: (UiEvent.Replace) -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val keyboardIsOpen by keyboardIsOpen()

    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Replace -> onReplace(event)
                else -> Unit
            }
        }
    }

    Scaffold(
        bottomBar = {
            Box(modifier = Modifier
                .background(Color.White.copy(alpha = .6f))
                .clickable {
                    viewModel.onEvent(RegisterEvent.LoginNavigateClick)
                }) {

                val text = stringResource(R.string.already_have_an_account)
                    .split("?")

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {

                    Text(
                        text = text[0],
                        textAlign = TextAlign.Center,
                        style = Typography.body2
                            .medium()
                    )
                    Text(
                        text = text[1],
                        textAlign = TextAlign.Center,
                        style = Typography.body1
                            .color(Blue)
                            .bold()
                            .interactive()
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(
                        horizontal = 52.dp,
                        vertical = 16.dp
                    )
            ) {
                // Top title
                Box(
                    contentAlignment = Alignment.BottomCenter
                ) {
                    if (!keyboardIsOpen)
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.illustration_playing_2),
                            contentDescription = "Register illustration",
                            modifier = Modifier.gradientShade()
                        )

                    Text(
                        text = stringResource(R.string.register),
                        style = Typography.h1
                    )
                }

                Spacer(modifier = Modifier.height(52.dp))

                // Form
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    FormField(
                        hasError = viewModel.state.error != null,
                        leading = ImageVector.vectorResource(R.drawable.ic_user),
                        type = TextFieldType.Name,
                        hint = stringResource(R.string.name),
                        imeAction = ImeAction.Next,
                        onValueChange = {
                            viewModel.onEvent(RegisterEvent.NameChanged(it))
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    FormField(
                        hasError = viewModel.state.error != null,
                        leading = ImageVector.vectorResource(R.drawable.ic_mail),
                        type = TextFieldType.Email,
                        hint = stringResource(R.string.email),
                        imeAction = ImeAction.Next,
                        onValueChange = {
                            viewModel.onEvent(RegisterEvent.EmailChanged(it))
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    FormField(
                        hasError = viewModel.state.error != null,
                        leading = ImageVector.vectorResource(R.drawable.ic_password),
                        type = TextFieldType.Password,
                        hint = stringResource(R.string.password),
                        imeAction = ImeAction.Done,
                        onValueChange = {
                            viewModel.onEvent(RegisterEvent.PasswordChanged(it))
                        },
                        onDone = { viewModel.onEvent(RegisterEvent.RegisterClick) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Button
                FormButton(
                    text = stringResource(R.string.register),
                    enabled = viewModel.state.buttonEnabled,
                    onClick = {
                        viewModel.onEvent(RegisterEvent.RegisterClick)
                    },
                    errorMessage = viewModel.state.error?.asString(),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}