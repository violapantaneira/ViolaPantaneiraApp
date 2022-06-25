package com.violapantaneira.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import com.violapantaneira.app.R
import com.violapantaneira.app.ui.theme.*
import com.violapantaneira.app.ui.util.interactive
import com.violapantaneira.app.ui.util.medium
import com.violapantaneira.app.util.TextFieldType

@Composable
fun FormField(
    modifier: Modifier = Modifier,
    leading: ImageVector,
    type: TextFieldType = TextFieldType.Text,
    hint: String,
    imeAction: ImeAction = ImeAction.Done,
    hasError: Boolean = false,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit = {}
) {
    var value by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var color by remember { mutableStateOf(Black.copy(alpha = .6f)) }

    val focus = LocalFocusManager.current

    val capitalization =
        if (type == TextFieldType.Name)
            KeyboardCapitalization.Words
        else
            KeyboardCapitalization.None
    val autoCorrect = type != TextFieldType.Password
    val keyboardType = when (type) {
        is TextFieldType.Text -> KeyboardType.Text
        is TextFieldType.Name -> KeyboardType.Text
        is TextFieldType.Email -> KeyboardType.Email
        is TextFieldType.Password -> KeyboardType.Password
    }

    TextField(
        value = value,
        textStyle = Typography.body1
            .interactive(),
        onValueChange = {
            value = it
            onValueChange(it)
        },
        placeholder = {
            Text(
                text = hint,
                style = Typography.body2
                    .medium()
                    .interactive()
            )
        },

        leadingIcon = {
            Image(
                imageVector = leading,
                contentDescription = "Leading icon",
                colorFilter = ColorFilter.tint(
                    color =
                    if (hasError) Red
                    else color
                )
            )
        },
        trailingIcon = {
            if (type == TextFieldType.Password && value.isNotBlank())
                Icon(
                    painter = painterResource(
                        if (isPasswordVisible)
                            R.drawable.ic_show
                        else
                            R.drawable.ic_hide
                    ),
                    contentDescription = "Show / Hide password",
                    tint = Black,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        })
                )
        },

        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Blue,
            unfocusedIndicatorColor = Black.copy(alpha = .6f),
            errorIndicatorColor = Red,
            cursorColor = Black
        ),

        visualTransformation =
        if (type == TextFieldType.Password && !isPasswordVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,

        keyboardOptions = KeyboardOptions(
            capitalization = capitalization,
            autoCorrect = autoCorrect,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onNext = { focus.moveFocus(FocusDirection.Down) },
            onDone = {
                focus.clearFocus()
                onDone()
            }
        ),
        modifier = modifier
            .onFocusChanged { focusState ->
                color =
                    if (focusState.isFocused)
                        Blue
                    else
                        Black.copy(alpha = .6f)
            }
    )
}