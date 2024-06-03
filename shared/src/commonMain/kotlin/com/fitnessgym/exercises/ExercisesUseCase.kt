package com.fitnessgym.exercises

import com.fitnessgym.db.entity.ExercisesEntity

class ExercisesUseCase(
    private val repository: ExercisesRepository
) {
    suspend fun selectExercises(): List<ExercisesEntity> =
        repository.selectExercises()
    suspend fun addExercises(entity: ExercisesEntity): Long =
        repository.addExercises(entity)

    suspend fun updateExercises(entity: ExercisesEntity): Long =
        repository.updateExercises(entity)

    suspend fun deleteExercises(id: Long): Long =
        repository.deleteExercises(id)
}
