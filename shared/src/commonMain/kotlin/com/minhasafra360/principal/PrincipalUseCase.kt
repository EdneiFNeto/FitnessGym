package com.minhasafra360.principal

import com.minhasafra360.db.entity.ExercisesEntity

class PrincipalUseCase(
    private val repository: PrincipalRepository
) {
    suspend fun getExercises(): List<ExercisesEntity> = repository.getExercises()

    fun saveExercises(entity: ExercisesEntity) {
        repository.saveExercises(entity)
    }
}
