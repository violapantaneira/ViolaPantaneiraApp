package com.violapantaneira.app.domain.model

import android.net.Uri
import com.violapantaneira.app.domain.repository.Rhythm

data class Song(
    val name: String = "",
    val author: String = "",
    val photoUrl: Uri? = null,
    val tune: String = "",
    val rhythm: Rhythm = Rhythm(),
    val lyrics: List<Map<String, String>> = emptyList()
)
