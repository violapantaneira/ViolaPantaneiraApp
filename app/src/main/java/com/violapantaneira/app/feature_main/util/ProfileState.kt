package com.violapantaneira.app.feature_main.util

import android.net.Uri

data class ProfileState(
    val editing: Boolean = false,
    val name: String = "",
    val photoUrl: Uri? = null
)
