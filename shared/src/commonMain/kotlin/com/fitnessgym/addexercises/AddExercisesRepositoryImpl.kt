package com.fitnessgym.addexercises

import com.fitnessgym.db.entity.ExercisesEntity
import com.fitnessgym.db.source.ExercisesDataSource

class AddExercisesRepositoryImpl(
    private val dataSource: ExercisesDataSource
): AddExercisesRepository {
    override suspend fun addExercises(entity: ExercisesEntity): Long  = dataSource.insertExercises(entity)
}