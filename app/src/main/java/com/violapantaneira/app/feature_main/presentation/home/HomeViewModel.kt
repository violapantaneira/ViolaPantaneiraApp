package com.violapantaneira.app.feature_main.presentation.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.violapantaneira.app.domain.model.Song
import com.violapantaneira.app.domain.repository.DatabaseRepository
import com.violapantaneira.app.domain.repository.Rhythm
import dagger.hilt.android.lifecycle.HiltViewModel
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

}
