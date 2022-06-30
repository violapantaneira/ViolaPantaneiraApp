package com.violapantaneira.app.domain.repository

import com.violapantaneira.app.domain.model.Date
import com.violapantaneira.app.domain.model.Roll
import com.violapantaneira.app.domain.model.Song
import com.violapantaneira.app.domain.model.User

interface DatabaseRepository {
    fun getAdmins(
        callback: (List<User>) -> Unit
    )

    fun getStudents(
        callback: (List<User>) -> Unit
    )

    fun getRoll(
        date: Date,
        callback: (Roll?) -> Unit
    )

    fun setRoll(roll: Roll)

    fun getRhythms(
        callback: (List<Rhythm>) -> Unit
    )

    fun getAllSongs(
        callback: (List<Song>) -> Unit
    )

    fun getSongsByRhythm(
        rhythm: Rhythm,
        callback: (List<Song>) -> Unit
    )

    fun getSongs(
        query: String,
        callback: (List<Song>) -> Unit
    )
}