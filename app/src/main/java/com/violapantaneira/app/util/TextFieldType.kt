package com.violapantaneira.app.util

sealed class TextFieldType {
    object Text : TextFieldType()
    object Name : TextFieldType()
    object Email : TextFieldType()
    object Password : TextFieldType()
}
