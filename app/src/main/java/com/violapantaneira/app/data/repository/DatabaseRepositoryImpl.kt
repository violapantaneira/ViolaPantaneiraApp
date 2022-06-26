package com.violapantaneira.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.violapantaneira.app.domain.model.User
import com.violapantaneira.app.domain.repository.DatabaseRepository

class DatabaseRepositoryImpl(
    private val database: FirebaseFirestore
) : DatabaseRepository {

    override fun getAdmins(callback: (List<User>?) -> Unit) {

        database.collection("admins")
            .get()
            .addOnSuccessListener { result ->
                val admins = mutableListOf<User>()

                val users = result.map { it.toObject(User::class.java) }
                users.forEach { user ->
                    admins.add(user)
                }

                callback(admins)
            }
    }
}