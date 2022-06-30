package com.violapantaneira.app.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.violapantaneira.app.R

// Set of Material typography styles to start with
val SpaceGroteskFamily = FontFamily(
    Font(R.font.spacegrotesk_regular, FontWeight.Normal),
    Font(R.font.spacegrotesk_medium, FontWeight.Medium),
    Font(R.font.spacegrotesk_bold, FontWeight.Bold),
)
val RalewayFamily = FontFamily(
    Font(R.font.raleway_regular, FontWeight.Normal),
    Font(R.font.raleway_medium, FontWeight.Medium),
    Font(R.font.raleway_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = RalewayFamily,

    h1 = TextStyle(
        color = Black,
        fontWeight = FontWeight.Bold,
        fontSize = 56.sp
    ),
    h2 = TextStyle(
        color = Black,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),

    body1 = TextStyle(
        color = Black,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        color = Black.copy(alpha = .6f),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    button = TextStyle(
        color = Black,
        fontFamily = SpaceGroteskFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
)