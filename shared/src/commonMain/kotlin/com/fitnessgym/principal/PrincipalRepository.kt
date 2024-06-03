package com.fitnessgym.principal

import com.fitnessgym.db.entity.ExercisesEntity

interface PrincipalRepository {
    suspend fun getExercises(): MutableList<ExercisesEntity>
    fun saveExercises(entity: ExercisesEntity)
}
