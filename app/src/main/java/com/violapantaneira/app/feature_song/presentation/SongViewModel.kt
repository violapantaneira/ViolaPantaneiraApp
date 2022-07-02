package com.violapantaneira.app.feature_song.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.violapantaneira.app.domain.model.Song
import com.violapantaneira.app.domain.repository.DatabaseRepository
import com.violapantaneira.app.feature_song.util.SongEvent
import com.violapantaneira.app.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    database: DatabaseRepository
) : ViewModel() {

    val songs = mutableStateListOf<Song>()

    init {
        database.getAllSongs { songs.addAll(it) }
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SongEvent) {
        when (event) {
            is SongEvent.PopButtonClicked -> {
                sendUiEvent(UiEvent.Pop)
            }
            is SongEvent.AuthorClicked -> {

            }
            is SongEvent.ChordClicked -> {

            }
            is SongEvent.RhythmClicked -> {

            }
            is SongEvent.TuneClicked -> {

            }
        }
    }

    private fun sendUiEvent(event: UiEvent) =
        viewModelScope.launch {
            _uiEvent.send(event)
        }

    fun getRows(lyrics: List<Map<String, String>>): List<List<Map<String, String>>> {
        val rows = mutableListOf<List<Map<String, String>>>()

        val uppercaseIndexes = lyrics
            .withIndex()
            .filter {
                if ((it.value["word"] ?: "").isBlank())
                    false
                else
                    it.value["word"]!![0].isUpperCase()
            }
            .map { it.index }

        uppercaseIndexes.indices.forEach { i ->
            val last = uppercaseIndexes.lastIndex
            val row = lyrics.subList(
                uppercaseIndexes[i],
                if (i == last)
                    lyrics.lastIndex + 1
                else
                    uppercaseIndexes[i + 1]
            )

            rows.add(row)
        }

        return rows
    }
}