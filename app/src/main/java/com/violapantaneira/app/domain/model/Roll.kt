package com.violapantaneira.app.domain.model

import android.net.Uri

data class Roll(
    val roll: List<RollItem> = emptyList()
)

data class RollItem(
    val name: String? = null,
    val email: String? = null,
    val photoUrl: Uri? = null,
    val checked: Boolean = false
)