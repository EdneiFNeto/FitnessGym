package com.fitnessgym.exercises

import com.fitnessgym.FetchStatus
import com.fitnessgym.db.entity.ExercisesEntity
import com.fitnessgym.principal.fakes

data class ExercisesUIState(
    val exercises: List<ExercisesEntity> = emptyList(),
    val error: String? = null,
    val fetchStatus: FetchStatus = FetchStatus.NONE
)
