package com.violapantaneira.app.feature_main.presentation.roll

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.violapantaneira.app.ui.components.RollItem

@Composable
fun RollScreen(
    viewModel: RollViewModel = hiltViewModel()
) {
    val students = remember { viewModel.roll }

    LazyColumn(
        contentPadding = PaddingValues(
            start = 24.dp,
            top = 24.dp,
            end = 24.dp,
            bottom = 110.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(students) { student ->
            RollItem(
                rollItem = student,
                onClick = { viewModel.setRoll(it) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
