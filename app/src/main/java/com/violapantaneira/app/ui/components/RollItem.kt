package com.violapantaneira.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.violapantaneira.app.R
import com.violapantaneira.app.domain.model.RollItem
import com.violapantaneira.app.ui.theme.*
import com.violapantaneira.app.ui.util.regular
import com.violapantaneira.app.ui.util.small

@Composable
fun RollItem(
    modifier: Modifier = Modifier,
    rollItem: RollItem,
    onClick: (RollItem) -> Unit
) {
    var checked by remember { mutableStateOf(rollItem.checked) }
    var applied by remember { mutableStateOf(checked) }

    val photo =
        if (rollItem.photoUrl == null)
            painterResource(R.drawable.ic_viola)
        else
            rememberAsyncImagePainter(rollItem.photoUrl)

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
        ) {
            Image(
                painter = photo,
                contentDescription = "Profile photo",
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
                    text = rollItem.name ?: "",
                    style = Typography.button,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = rollItem.email ?: "",
                    style = Typography.button
                        .small()
                        .regular(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            if (applied)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (checked)
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = "Check",
                            tint = Black.copy(alpha = .6f)
                        )
                    else
                        Icon(
                            painter = painterResource(R.drawable.ic_x),
                            contentDescription = "Uncheck",
                            tint = Black.copy(alpha = .6f)
                        )

                    IconButton(onClick = {
                        applied = false
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_edit),
                            contentDescription = "Edit",
                            tint = Blue
                        )
                    }
                }

            if (!applied)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Uncheck button
                    IconButton(onClick = {
                        checked = false
                        applied = true
                        onClick(
                            RollItem(
                                rollItem.name,
                                rollItem.email,
                                rollItem.photoUrl,
                                checked
                            )
                        )
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_x),
                            contentDescription = "Uncheck",
                            tint = Red
                        )
                    }
                    // Check button
                    IconButton(
                        onClick = {
                            checked = true
                            applied = true
                            onClick(
                                RollItem(
                                    rollItem.name,
                                    rollItem.email,
                                    rollItem.photoUrl,
                                    checked
                                )
                            )
                        },
                        modifier = Modifier.background(
                            color = Blue,
                            shape = RoundedCornerShape(12.dp)
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = "Check",
                            tint = Color.White
                        )
                    }
                }
        }
    }
}