package com.violapantaneira.app.domain.model

data class Date(
    val day: Int,
    val month: Int,
    val year: Int
) {
    override fun toString(): String =
        "$day.$month.$year"
}
