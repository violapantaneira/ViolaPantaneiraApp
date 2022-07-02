package com.violapantaneira.app.feature_main.presentation.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.violapantaneira.app.domain.model.Song
import com.violapantaneira.app.domain.repository.DatabaseRepository
import com.violapantaneira.app.domain.repository.Rhythm
import com.violapantaneira.app.feature_main.util.HomeEvent
import com.violapantaneira.app.navigation.SongRoutes
import com.violapantaneira.app.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val database: DatabaseRepository
) : ViewModel() {

    val rhythms = mutableStateListOf<Rhythm>()
    val songs = mutableStateListOf<Song>()

    init {
        database.getRhythms { rhythms.addAll(it) }
        database.getAllSongs { songs.addAll(it) }
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: HomeEvent) =
        when (event) {
            is HomeEvent.SongClicked -> {
                val song = event.song

                sendUiEvent(
                    UiEvent.Navigate(
                        SongRoutes.argument(song.id)
                    )
                )
            }
        }

    private fun sendUiEvent(event: UiEvent) =
        viewModelScope.launch {
            _uiEvent.send(event)
        }
}
