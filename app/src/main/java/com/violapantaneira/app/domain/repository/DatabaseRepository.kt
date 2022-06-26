package com.violapantaneira.app.domain.repository

import com.violapantaneira.app.domain.model.User

interface DatabaseRepository {
    fun getAdmins(callback: (List<User>?) -> Unit)
}