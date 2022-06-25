package com.violapantaneira.app.ui.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Add a **gradient shader**
 */
fun Modifier.gradientShade(): Modifier =
    graphicsLayer { alpha = .99f }
        .drawWithContent {
            val colors = listOf(
                Color.Black,
                Color.Black,
                Color.Transparent
            )
            drawContent()
            drawRect(
                brush = Brush.verticalGradient(colors),
                blendMode = BlendMode.DstIn
            )
        }