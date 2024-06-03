package com.fitnessgym.exercises

import com.fitnessgym.db.entity.ExercisesEntity

interface ExercisesRepository {
    suspend fun selectExercises(): List<ExercisesEntity>
    suspend fun addExercises(entity: ExercisesEntity): Long
    suspend fun updateExercises(entity: ExercisesEntity): Long
    suspend fun deleteExercises(id: Long): Long
}
