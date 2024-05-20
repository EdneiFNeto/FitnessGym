package com.minhasafra360.addexercises

import com.minhasafra360.db.entity.ExercisesEntity

sealed class AddExercisesEvent {
    data class OnSaveEntity(val entity: ExercisesEntity): AddExercisesEvent()
}
