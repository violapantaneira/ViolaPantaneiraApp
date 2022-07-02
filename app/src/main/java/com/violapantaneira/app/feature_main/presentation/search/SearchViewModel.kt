package com.violapantaneira.app.feature_main.presentation.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.violapantaneira.app.domain.model.Song
import com.violapantaneira.app.domain.repository.DatabaseRepository
import com.violapantaneira.app.feature_main.util.SearchEvent
import com.violapantaneira.app.feature_main.util.SearchState
import com.violapantaneira.app.navigation.SongRoutes
import com.violapantaneira.app.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val database: DatabaseRepository
) : ViewModel() {

    var state by mutableStateOf(SearchState())

    val songs = mutableStateListOf<Song>()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchChanged -> {
                state = state.copy(searchValue = event.value)
                database.getSongs(state.searchValue) {
                    songs.clear()
                    songs.addAll(it)
                    Log.i("Songs", it.toString())
                }
            }
            is SearchEvent.SongClicked -> {
                sendUiEvent(UiEvent.Navigate(SongRoutes.argument(event.song.id)))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) =
        viewModelScope.launch {
            _uiEvent.send(event)
        }
}