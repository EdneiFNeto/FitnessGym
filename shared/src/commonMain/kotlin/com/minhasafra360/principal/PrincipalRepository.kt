package com.minhasafra360.principal

import com.minhasafra360.db.entity.ExercisesEntity

interface PrincipalRepository {
    suspend fun getExercises(): List<ExercisesEntity>
    fun saveExercises(entity: ExercisesEntity)
}
