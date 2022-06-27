package com.violapantaneira.app.feature_main.use_case

import com.violapantaneira.app.domain.model.Roll
import com.violapantaneira.app.domain.model.RollItem
import com.violapantaneira.app.domain.repository.CalendarRepository
import com.violapantaneira.app.domain.repository.DatabaseRepository

class GetRollUseCase(
    private val database: DatabaseRepository,
    private val calendar: CalendarRepository
) : IMainUseCases.GetRoll {

    override fun invoke(callback: (Roll) -> Unit) {
        database.getRoll(calendar.date) { roll ->
            if (roll == null) {
                database.getStudents { students ->
                    val studentRoll = mutableListOf<RollItem>()

                    students.forEach { student ->
                        val rollItem = RollItem(
                            name = student.name,
                            email = student.email,
                            photoUrl = student.photoUrl,
                            checked = false
                        )
                        studentRoll.add(rollItem)
                    }

                    callback(Roll(studentRoll))
                }
            } else {
                callback(roll)
            }
        }
    }
}