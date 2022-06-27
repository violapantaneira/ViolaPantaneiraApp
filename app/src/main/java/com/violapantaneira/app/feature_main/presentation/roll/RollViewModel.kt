package com.violapantaneira.app.feature_main.presentation.roll

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.violapantaneira.app.domain.model.Roll
import com.violapantaneira.app.domain.model.RollItem
import com.violapantaneira.app.domain.repository.DatabaseRepository
import com.violapantaneira.app.feature_main.use_case.MainUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RollViewModel @Inject constructor(
    useCases: MainUseCases,
    val database: DatabaseRepository
) : ViewModel() {

    val roll = mutableStateListOf<RollItem>()

    init {
        useCases.getRoll { roll.addAll(it.roll) }
    }

    fun setRoll(rollItem: RollItem) {
        val index = roll.map { it.name }.indexOf(rollItem.name)

        roll[index] = rollItem

        val roll = Roll(roll)
        database.setRoll(roll)
    }
}
