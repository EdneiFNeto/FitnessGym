package com.fitnessgym.principal

import com.fitnessgym.db.entity.ExercisesEntity

class PrincipalUseCase(
    private val repository: PrincipalRepository
) {
    suspend fun getExercises(): MutableList<ExercisesEntity> = repository.getExercises()

    fun saveExercises(entity: ExercisesEntity) {
        repository.saveExercises(entity)
    }
}
