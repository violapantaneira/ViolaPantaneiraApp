package com.violapantaneira.app.feature_main.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.violapantaneira.app.domain.model.Song
import com.violapantaneira.app.domain.repository.Rhythm
import com.violapantaneira.app.feature_main.util.HomeEvent
import com.violapantaneira.app.ui.components.SongItem
import com.violapantaneira.app.ui.theme.Typography
import com.violapantaneira.app.util.UiEvent

@Composable
fun HomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    val rhythms = remember { viewModel.rhythms }
    val songs = remember { viewModel.songs }

    LazyColumn(
        contentPadding = PaddingValues(24.dp)
    ) {
        rhythms.forEach { rhythm ->
            val filteredSongs = songs.where(rhythm)

            item {
                Text(
                    text = rhythm.name,
                    style = Typography.h3
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            itemsIndexed(filteredSongs) { index, song ->
                SongItem(
                    song = song,
                    onClick = {
                        viewModel.onEvent(HomeEvent.SongClicked(it))
                        Log.i("SongId", it.toString())
                    }
                )

                if (index != rhythms.lastIndex)
                    Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

private fun List<Song>.where(rhythm: Rhythm): List<Song> =
    filter { it.rhythm == rhythm }
