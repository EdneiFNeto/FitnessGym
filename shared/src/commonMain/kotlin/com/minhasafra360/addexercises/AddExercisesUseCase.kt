package com.minhasafra360.addexercises

import com.minhasafra360.db.entity.ExercisesEntity

class AddExercisesUseCase(
    private val repository: AddExercisesRepository
) {
    suspend fun addExercises(entity: ExercisesEntity): Long = repository.addExercises(entity)

}
