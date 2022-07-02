package com.violapantaneira.app.ui.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.violapantaneira.app.ui.theme.Blue
import com.violapantaneira.app.ui.theme.Typography

@Composable
fun DefaultSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                action = {
                    data.actionLabel?.let { actionLabel ->
                        TextButton(
                            onClick = { onDismiss() }
                        ) {
                            Text(
                                text = actionLabel,
                                style = Typography.button,
                                color = Blue
                            )
                        }
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = data.message,
                    style = Typography.body1
                        .color(Color.White)
                )
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom)
    )
}