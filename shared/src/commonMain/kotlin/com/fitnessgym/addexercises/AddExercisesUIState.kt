package com.fitnessgym.addexercises

import com.fitnessgym.FetchStatus
import com.fitnessgym.db.entity.ExercisesEntity

data class AddExercisesUIState(
    val entity: ExercisesEntity? = null,
    val error: String? = null,
    val status: FetchStatus = FetchStatus.NONE
)
