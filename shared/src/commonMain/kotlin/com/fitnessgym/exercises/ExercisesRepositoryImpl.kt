package com.fitnessgym.exercises

import com.fitnessgym.db.entity.ExercisesEntity
import com.fitnessgym.db.source.ExercisesDataSource

class ExercisesRepositoryImpl(
    private val dataSource: ExercisesDataSource
) : ExercisesRepository {
    override suspend fun selectExercises(): List<ExercisesEntity> =
        dataSource.selectExercises()

    override suspend fun addExercises(entity: ExercisesEntity): Long =
        dataSource.insertExercises(entity)

    override suspend fun updateExercises(entity: ExercisesEntity): Long =
        dataSource.updateExercises(entity)

    override suspend fun deleteExercises(id: Long): Long =
        dataSource.deleteExercises(id)
}