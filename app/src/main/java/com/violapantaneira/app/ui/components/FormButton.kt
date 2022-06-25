package com.violapantaneira.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.violapantaneira.app.ui.theme.Blue
import com.violapantaneira.app.ui.theme.Red
import com.violapantaneira.app.ui.theme.Typography
import com.violapantaneira.app.ui.theme.White
import com.violapantaneira.app.ui.util.*

@Composable
fun FormButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = false,
    errorMessage: String? = null,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Button
        Button(
            modifier = modifier,
            enabled = enabled,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Blue,
                disabledBackgroundColor =
                if (errorMessage == null)
                    Blue.copy(alpha = .6f)
                else
                    Red.copy(alpha = .6f),
            ),
            onClick = onClick
        ) {
            Text(
                text = text,
                style = Typography.body1
                    .color(White)
                    .bold()
                    .interactive(),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Error message
        if (errorMessage != null) Text(
            text = errorMessage,
            style = Typography.body1
                .color(Red)
                .medium()
                .small(),
            textAlign = TextAlign.Center,
        )
    }
}
