package com.violapantaneira.app.feature_song.util

sealed class SongEvent {
    object PopButtonClicked : SongEvent()

    object AuthorClicked : SongEvent()

    object TuneClicked : SongEvent()

    object RhythmClicked : SongEvent()

    data class ChordClicked(val chord: String) : SongEvent()
}
