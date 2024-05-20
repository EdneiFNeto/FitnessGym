package com.fitnessgym.addexercises

import com.fitnessgym.db.entity.ExercisesEntity

interface AddExercisesRepository {
    suspend fun addExercises(entity: ExercisesEntity): Long
}
