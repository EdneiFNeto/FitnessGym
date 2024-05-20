package com.fitnessgym.addexercises

import com.fitnessgym.db.entity.ExercisesEntity

class AddExercisesUseCase(
    private val repository: AddExercisesRepository
) {
    suspend fun addExercises(entity: ExercisesEntity): Long = repository.addExercises(entity)

}
