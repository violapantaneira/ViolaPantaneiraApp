package com.violapantaneira.app.ui.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.violapantaneira.app.ui.theme.SpaceGroteskFamily

fun TextStyle.color(color: Color): TextStyle = copy(
    color = color
)

fun TextStyle.interactive(): TextStyle = copy(
    fontFamily = SpaceGroteskFamily
)

fun TextStyle.regular(): TextStyle = copy(
    fontWeight = FontWeight.Normal
)

fun TextStyle.medium(): TextStyle = copy(
    fontWeight = FontWeight.Medium
)

fun TextStyle.bold(): TextStyle = copy(
    fontWeight = FontWeight.Bold
)

fun TextStyle.small(): TextStyle = copy(
    fontSize = 14.sp
)

fun TextStyle.standard(): TextStyle = copy(
    fontSize = 16.sp
)

fun TextStyle.large(): TextStyle = copy(
    fontSize = 20.sp
)