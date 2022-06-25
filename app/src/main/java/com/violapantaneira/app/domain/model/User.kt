package com.violapantaneira.app.domain.model

import android.net.Uri

data class User(
    val email: String? = null,
    val name: String? = null,
    val photoUrl: Uri? = null
)
