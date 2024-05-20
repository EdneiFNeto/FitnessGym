package com.minhasafra360.addexercises

import com.minhasafra360.db.entity.ExercisesEntity
import com.minhasafra360.db.source.ExercisesDataSource

class AddExercisesRepositoryImpl(
    private val dataSource: ExercisesDataSource
): AddExercisesRepository {
    override suspend fun addExercises(entity: ExercisesEntity): Long  = 0L
}