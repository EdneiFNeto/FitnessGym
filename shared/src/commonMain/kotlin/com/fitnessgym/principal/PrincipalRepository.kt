package com.fitnessgym.principal

import com.fitnessgym.db.entity.ExercisesEntity

interface PrincipalRepository {
    suspend fun getExercises(): ArrayList<ExercisesEntity>
    fun saveExercises(entity: ExercisesEntity)
}
