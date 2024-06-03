package com.fitnessgym.exercises

import com.fitnessgym.db.entity.ExercisesEntity

sealed class ExercisesEvent {
    data class OnAddExercises(val entity: ExercisesEntity): ExercisesEvent()
    data class OnUpdateExercises(val data: Map<String, String>): ExercisesEvent()
    data class OnDeleteExercises(val data: Map<String, String>): ExercisesEvent()
    data object OnDoneFetch: ExercisesEvent()
}
