package com.violapantaneira.app.data.repository

import com.violapantaneira.app.domain.model.Date
import com.violapantaneira.app.domain.repository.CalendarRepository
import java.util.Calendar

class CalendarRepositoryImpl : CalendarRepository {

    override lateinit var date: Date

    init {
        val c = Calendar.getInstance()
        c.time = java.util.Date()

        date = Date(
            day = c.get(Calendar.DATE),
            month = c.get(Calendar.MONTH),
            year = c.get(Calendar.YEAR)
        )
    }
}