package com.minhasafra360.addexercises

import com.minhasafra360.db.entity.ExercisesEntity

interface AddExercisesRepository {
    suspend fun addExercises(entity: ExercisesEntity): Long
}
