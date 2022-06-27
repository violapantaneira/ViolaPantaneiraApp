package com.violapantaneira.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.violapantaneira.app.domain.model.Date
import com.violapantaneira.app.domain.model.Roll
import com.violapantaneira.app.domain.model.User
import com.violapantaneira.app.domain.repository.CalendarRepository
import com.violapantaneira.app.domain.repository.DatabaseRepository

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
}
