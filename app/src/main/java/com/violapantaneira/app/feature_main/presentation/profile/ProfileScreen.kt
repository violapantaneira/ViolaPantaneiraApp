package com.violapantaneira.app.feature_main.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.violapantaneira.app.R
import com.violapantaneira.app.feature_main.util.ProfileEvent
import com.violapantaneira.app.ui.components.IconTextButton
import com.violapantaneira.app.ui.theme.Red
import com.violapantaneira.app.ui.theme.Typography
import com.violapantaneira.app.ui.util.medium
import com.violapantaneira.app.util.UiEvent

@Composable
fun ProfileScreen(
    onReplace: (UiEvent.Replace) -> Unit,
    showSnackbar: (UiEvent.ShowSnackbar) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Replace -> {
                    onReplace(event)
                }
                is UiEvent.ShowSnackbar -> {
                    showSnackbar(event)
                }
                else -> {}
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 52.dp,
                vertical = 24.dp
            )
            .verticalScroll(scrollState)
    ) {

        // Profile info
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val photo =
                if (viewModel.user?.photoUrl == null)
                    painterResource(R.drawable.ic_viola)
                else
                    rememberAsyncImagePainter(viewModel.user.photoUrl)
            // Profile photo
            Image(
                painter = photo,
                contentDescription = "Profile photo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Name
            Text(
                text = viewModel.user?.name!!,
                style = Typography.h3
            )

            // Email
            Text(
                text = viewModel.user.email!!,
                style = Typography.body2
                    .medium()
            )
        }

        // Buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Edit profile
            IconTextButton(
                icon = painterResource(R.drawable.ic_edit),
                text = stringResource(R.string.edit_profile),
                onClick = {
                    viewModel.onEvent(ProfileEvent.ToggleProfileEditingClicked)
                }
            )
            // Logout
            IconTextButton(
                icon = painterResource(R.drawable.ic_logout),
                text = stringResource(R.string.log_out),
                contentColor = Red,
                onClick = {
                    viewModel.onEvent(ProfileEvent.SignOutClicked)
                }
            )
        }
    }
}