package com.minhasafra360.addexercises

import com.minhasafra360.FetchStatus
import com.minhasafra360.db.entity.ExercisesEntity

data class AddExercisesUIState(
    val entity: ExercisesEntity? = null,
    val error: String? = null,
    val status: FetchStatus = FetchStatus.NONE
)
