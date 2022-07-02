package com.violapantaneira.app.feature_main.util

import com.violapantaneira.app.domain.model.Song

sealed class HomeEvent {
    data class CardClicked(val card: HomeCard) : HomeEvent()

    data class SongClicked(val song: Song) : HomeEvent()
}
