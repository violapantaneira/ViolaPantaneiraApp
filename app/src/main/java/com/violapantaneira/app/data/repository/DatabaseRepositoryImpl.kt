package com.violapantaneira.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.violapantaneira.app.domain.model.Date
import com.violapantaneira.app.domain.model.Roll
import com.violapantaneira.app.domain.model.Song
import com.violapantaneira.app.domain.model.User
import com.violapantaneira.app.domain.repository.CalendarRepository
import com.violapantaneira.app.domain.repository.DatabaseRepository
import com.violapantaneira.app.domain.repository.Rhythm

class DatabaseRepositoryImpl(
    private val database: FirebaseFirestore,
    private val calendar: CalendarRepository
) : DatabaseRepository {

    override fun getAdmins(callback: (List<User>) -> Unit) {
        database.collection("admins")
            .get()
            .addOnSuccessListener { result ->
                val users = result.map { it.toObject(User::class.java) }
                callback(users)
            }
    }

    override fun getStudents(callback: (List<User>) -> Unit) {
        database.collection("students")
            .get()
            .addOnSuccessListener { result ->
                val users = result.map { it.toObject(User::class.java) }
                callback(users)
            }
    }

    override fun getRoll(date: Date, callback: (Roll?) -> Unit) {
        database.collection("roll")
            .document(date.toString())
            .get()
            .addOnSuccessListener { result ->
                val roll = result.toObject(Roll::class.java)
                callback(roll)
            }
    }

    override fun setRoll(roll: Roll) {
        val date = calendar.date.toString()

        val ref = database.collection("roll")
            .document(date)

        ref.set(roll)
    }

    override fun getRhythms(callback: (List<Rhythm>) -> Unit) {
        database.collection("rhythms")
            .get()
            .addOnSuccessListener { result ->
                val rhythms = result.map { it.toObject(Rhythm::class.java) }
                callback(rhythms)
            }
    }

    override fun getAllSongs(callback: (List<Song>) -> Unit) {
        database.collection("songs")
            .get()
            .addOnSuccessListener { result ->
                val songs = result.map { document ->
                    document.toObject(Song::class.java).also {
                        it.id = document.id
                    }
                }
                callback(songs)
            }
    }

    override fun getSongsByRhythm(rhythm: Rhythm, callback: (List<Song>) -> Unit) {
        database.collection("songs")
            .get()
            .addOnSuccessListener { result ->
                val songs = result
                    .map { it.toObject(Song::class.java) }
                    .filter { it.rhythm == rhythm }
                callback(songs)
            }
    }

    override fun getSongs(query: String, callback: (List<Song>) -> Unit) {
        database.collection("songs")
            .get()
            .addOnSuccessListener { result ->
                val songs = result.map { it.toObject(Song::class.java) }
                    .filter {
                        it.name == query
                                || it.author == query
                                || it.rhythm.name == query
                                || it.tune == query
                    }
                callback(songs)
            }
    }
}
