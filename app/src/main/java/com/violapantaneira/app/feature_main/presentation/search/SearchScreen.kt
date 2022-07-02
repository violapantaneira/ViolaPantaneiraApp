package com.violapantaneira.app.feature_main.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.violapantaneira.app.R
import com.violapantaneira.app.feature_main.util.SearchEvent
import com.violapantaneira.app.ui.components.FormField
import com.violapantaneira.app.ui.components.SongItem
import com.violapantaneira.app.util.UiEvent

@Composable
fun SearchScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }
                else -> Unit
            }
        }
    }

    val songs = remember { viewModel.songs }

    Scaffold(topBar = {
        FormField(
            leading = ImageVector.vectorResource(R.drawable.ic_search),
            hint = stringResource(R.string.search),
            onValueChange = {
                viewModel.onEvent(SearchEvent.SearchChanged(it))
            },
            imeAction = ImeAction.Done,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }) { padding ->
        if (viewModel.state.searchValue.isNotBlank())
            LazyColumn(
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(padding)
            ) {
                items(songs) { song ->
                    SongItem(
                        song = song,
                        onClick = {
                            viewModel.onEvent(SearchEvent.SongClicked(it))
                        }
                    )
                }
            }
    }
}