package com.violapantaneira.app.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.violapantaneira.app.ui.theme.Black
import com.violapantaneira.app.ui.theme.Typography
import com.violapantaneira.app.ui.theme.isLight
import com.violapantaneira.app.ui.util.color
import com.violapantaneira.app.ui.util.large
import com.violapantaneira.app.ui.util.medium


@Composable
fun IconCard(
    modifier: Modifier = Modifier,
    item: IconCardItem,
    onClick: (IconCardItem) -> Unit
) {
    Box(
        modifier = modifier
            .clickable { onClick(item) }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = item.color,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 32.dp
                )
        ) {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = "${item.text} icon",
                tint =
                if (item.color.isLight())
                    Black
                else
                    Color.White
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = stringResource(item.text),
                style = Typography.button
                    .color(
                        if (item.color.isLight())
                            Black
                        else
                            Color.White
                    )
                    .medium()
                    .large(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

open class IconCardItem(
    @DrawableRes open val icon: Int,
    @StringRes open val text: Int,
    open val color: Color
)