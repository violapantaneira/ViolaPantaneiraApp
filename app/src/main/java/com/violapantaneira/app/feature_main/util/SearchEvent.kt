package com.violapantaneira.app.feature_main.util

import com.violapantaneira.app.domain.model.Song

sealed class SearchEvent {
    data class SongClicked(val song: Song) : SearchEvent()

    data class SearchChanged(val value: String) : SearchEvent()
}
