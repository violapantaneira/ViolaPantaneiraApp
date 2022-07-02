package com.violapantaneira.app.feature_song.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.violapantaneira.app.R
import com.violapantaneira.app.domain.model.Song
import com.violapantaneira.app.feature_song.util.SongEvent
import com.violapantaneira.app.ui.theme.Black
import com.violapantaneira.app.ui.theme.Blue
import com.violapantaneira.app.ui.theme.Typography
import com.violapantaneira.app.ui.util.*
import com.violapantaneira.app.util.UiEvent

@Composable
fun SongScreen(
    onPop: (UiEvent.Pop) -> Unit,
    songId: String,
    viewModel: SongViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Pop -> {
                    onPop(event)
                }
                else -> Unit
            }
        }
    }

    val songs = remember { viewModel.songs }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Row {
                IconButton(
                    onClick = {
                        viewModel.onEvent(SongEvent.PopButtonClicked)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_left),
                        contentDescription = "Pop",
                        tint = Black
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier.verticalScroll(state = scrollState)
        ) {
            if (songs.isNotEmpty()) {
                val song = songs.where(songId)

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(24.dp)
                ) {
                    // Title
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = song.name,
                            style = Typography.h2
                        )
                        Text(
                            text = song.author,
                            style = Typography.h3
                                .color(Blue)
                                .interactive(),
                            modifier = Modifier.clickable { viewModel.onEvent(SongEvent.AuthorClicked) }
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Song info
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Tune
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                text = stringResource(R.string.tune) + ":",
                                style = Typography.body1
                                    .large()
                                    .bold()
                            )
                            Text(
                                text = song.tune,
                                style = Typography.body1
                                    .large()
                                    .bold()
                                    .color(Blue)
                                    .interactive(),
                                modifier = Modifier.clickable {
                                    viewModel.onEvent(
                                        SongEvent.TuneClicked
                                    )
                                }
                            )
                        }

                        // Intro
                        FlowRow(
                            mainAxisSpacing = 8.dp
                        ) {
                            Text(
                                text = stringResource(R.string.intro) + ":",
                                style = Typography.body1
                                    .large()
                                    .bold()
                            )
                            song.intro.forEach { chord ->
                                Text(
                                    text = chord,
                                    style = Typography.body1
                                        .large()
                                        .bold()
                                        .color(Blue)
                                        .interactive(),
                                    modifier = Modifier.clickable {
                                        viewModel.onEvent(
                                            SongEvent.ChordClicked(
                                                chord
                                            )
                                        )
                                    }
                                )
                            }

                        }

                        // Rhythm
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                text = stringResource(R.string.rhythm) + ":",
                                style = Typography.body1
                                    .large()
                                    .bold()
                            )
                            Text(
                                text = song.rhythm.name,
                                style = Typography.body1
                                    .large()
                                    .bold()
                                    .color(Blue)
                                    .interactive(),
                                modifier = Modifier.clickable {
                                    viewModel.onEvent(
                                        SongEvent.RhythmClicked
                                    )
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Lyrics
                    Column(modifier = Modifier.fillMaxWidth()) {
                        val rows = viewModel.getRows(song.lyrics)
                        rows.forEach { lyrics ->
                            FlowRow(
                                mainAxisSpacing = 4.dp,
                            ) {
                                lyrics.forEach { lyric ->
                                    val chord = lyric["chord"].toString()
                                    val word = lyric["word"].toString()

                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = chord,
                                            style = Typography.body1
                                                .large()
                                                .bold()
                                                .color(Blue)
                                                .interactive(),
                                            modifier = Modifier.clickable {
                                                viewModel.onEvent(
                                                    SongEvent.TuneClicked
                                                )
                                            }
                                        )
                                        Text(
                                            text = word,
                                            style = Typography.body1
                                                .large()
                                                .medium()
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun List<Song>.where(songId: String): Song =
    filter { it.id == songId }[0]