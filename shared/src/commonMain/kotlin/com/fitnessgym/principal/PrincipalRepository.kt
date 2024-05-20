package com.fitnessgym.principal

import com.fitnessgym.db.entity.ExercisesEntity

interface PrincipalRepository {
    suspend fun getExercises(): List<ExercisesEntity>
    fun saveExercises(entity: ExercisesEntity)
}
