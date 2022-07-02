package com.violapantaneira.app.ui.util

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun <T> LazyListScope.gridItems(
    modifier: Modifier = Modifier,
    data: List<T>,
    columnCount: Int,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    itemContent: @Composable BoxScope.(T) -> Unit
) {
    val size = data.count()
    val rows = if (size == 0) 0 else 1 + (size - 1) / columnCount

    items(
        rows,
        key = { it.hashCode() }
    ) { rowIndex ->
        Row(
            horizontalArrangement = horizontalArrangement,
            modifier = modifier
        ) {
            for (columnIndex in 0 until columnCount) {
                val itemIndex = rowIndex * columnCount + columnIndex
                if (itemIndex < size) {
                    Box(
                        modifier = Modifier.weight(
                            1f,
                            fill = true
                        )
                    ) {
                        itemContent(data[itemIndex])
                    }
                } else {
                    Spacer(
                        modifier = Modifier.weight(
                            1f,
                            fill = true
                        )
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
    }
}