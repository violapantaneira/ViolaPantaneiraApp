package com.violapantaneira.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.violapantaneira.app.ui.theme.Black
import com.violapantaneira.app.ui.theme.Typography
import com.violapantaneira.app.ui.theme.White
import com.violapantaneira.app.ui.util.color
import com.violapantaneira.app.ui.util.medium

@Composable
fun IconTextButton(
    icon: Painter,
    text: String,
    backgroundColor: Color = White,
    contentColor: Color = Black,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(
                    horizontal = 24.dp,
                    vertical = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = icon,
                contentDescription = "$text button",
                tint = contentColor
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                style = Typography.button
                    .color(contentColor)
                    .medium()
            )
        }
    }
}