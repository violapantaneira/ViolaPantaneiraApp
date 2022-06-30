package com.violapantaneira.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.violapantaneira.app.R
import com.violapantaneira.app.domain.model.Song
import com.violapantaneira.app.ui.theme.Typography
import com.violapantaneira.app.ui.theme.White
import com.violapantaneira.app.ui.util.regular
import com.violapantaneira.app.ui.util.small

@Composable
fun SongItem(
    modifier: Modifier = Modifier,
    song: Song,
    onClick: (Song) -> Unit
) {
    val photo =
        if (song.photoUrl == null)
            painterResource(R.drawable.ic_viola)
        else
            rememberAsyncImagePainter(song.photoUrl)

    Box(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = White.copy(alpha = .6f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 20.dp
                )
                .clickable { onClick(song) }
        ) {
            Image(
                painter = photo,
                contentDescription = "Song photo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = song.name,
                    style = Typography.button,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = song.author,
                    style = Typography.button
                        .small()
                        .regular(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}