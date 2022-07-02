package com.violapantaneira.app.feature_main.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.violapantaneira.app.R
import com.violapantaneira.app.feature_main.util.ProfileEvent
import com.violapantaneira.app.ui.theme.Black
import com.violapantaneira.app.ui.theme.Typography
import com.violapantaneira.app.ui.util.interactive
import com.violapantaneira.app.ui.util.medium
import com.violapantaneira.app.util.UiEvent

@Composable
fun ProfileScreen(
    onReplace: (UiEvent.Replace) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Replace -> {
                    onReplace(event)
                }
                else -> {}
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            val photo =
                if (viewModel.user?.photoUrl == null)
                    painterResource(R.drawable.ic_viola)
                else
                    rememberAsyncImagePainter(viewModel.user.photoUrl)
            Image(
                painter = photo,
                contentDescription = "Profile photo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(80.dp)
            )

            Column {
                if (viewModel.state.editing)
                    TextField(
                        value = viewModel.state.name,
                        onValueChange = {
                            viewModel.onEvent(ProfileEvent.NameChanged(it))
                        },
                        textStyle = Typography.h3
                            .interactive(),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth(.5f)
                    )
                else
                    Text(
                        text = viewModel.user?.name!!,
                        style = Typography.h3
                    )

                Text(
                    text = viewModel.user?.email!!,
                    style = Typography.body2
                        .medium()
                )
            }

            val icon =
                if (viewModel.state.editing)
                    R.drawable.ic_check
                else
                    R.drawable.ic_edit
            IconButton(onClick = {
                viewModel.onEvent(ProfileEvent.ToggleProfileEditingClicked)
            }) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Edit",
                    tint = Black
                )
            }
        }

        Button(
            onClick = {
                viewModel.onEvent(ProfileEvent.SignOutClicked)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.log_out),
                style = Typography.button
                    .medium()
            )
        }
    }
}