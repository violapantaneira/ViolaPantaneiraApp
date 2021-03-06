package com.violapantaneira.app.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

val Black = Color(0xFF071013)
val White = Color(0xFFEAEAEA)
val WhiteGray = Color(0xFFF6F6F6)

val Blue = Color(0xFF00A5E0)
val Red = Color(0xFFFF101F)

@Composable
fun Color.isLight() = luminance() > .5
