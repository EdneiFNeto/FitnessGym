package com.fitnessgym.addexercises

import com.fitnessgym.db.entity.ExercisesEntity

sealed class AddExercisesEvent {
    data class OnSaveEntity(val entity: ExercisesEntity): AddExercisesEvent()
    data object OnDoneFetch: AddExercisesEvent()
}
