package com.violapantaneira.app.feature_main.util

import androidx.compose.ui.graphics.Color
import com.violapantaneira.app.R
import com.violapantaneira.app.ui.components.IconCardItem
import com.violapantaneira.app.ui.theme.Blue
import com.violapantaneira.app.ui.theme.White

sealed class HomeCard(
    override val icon: Int,
    override val text: Int,
    override val color: Color
) : IconCardItem(
    icon = icon,
    text = text,
    color = color
) {

    data class From(val cardItem: IconCardItem) : HomeCard(
        cardItem.icon,
        cardItem.text,
        cardItem.color
    )


    data class Chat(
        override val icon: Int = R.drawable.ic_chat,
        override val text: Int = R.string.chat,
        override val color: Color = Blue
    ) : HomeCard(icon, text, color)

    data class Rhythm(
        override val icon: Int = R.drawable.ic_rhythm,
        override val text: Int = R.string.rhythms,
        override val color: Color = White.copy(alpha = .6f)
    ) : HomeCard(icon, text, color)

    data class Metronome(
        override val icon: Int = R.drawable.ic_metronome,
        override val text: Int = R.string.metronome,
        override val color: Color = White.copy(alpha = .6f)
    ) : HomeCard(icon, text, color)

    data class Tuner(
        override val icon: Int = R.drawable.ic_tuner,
        override val text: Int = R.string.tuner,
        override val color: Color = White.copy(alpha = .6f)
    ) : HomeCard(icon, text, color)

    companion object {
        fun cards(): List<HomeCard> = listOf(
            Chat(),
            Rhythm(),
            Metronome(),
            Tuner()
        )
    }
}
