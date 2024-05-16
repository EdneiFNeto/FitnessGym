package com.minhasafra360.db.source

import com.minhasafra360.db.AppDatabase
import com.minhasafra360.db.entity.ExercisesEntity

class ExercisesDataSource(private val database: AppDatabase) {
    fun selectExercises(): List<ExercisesEntity> =
        database.appDatabaseQueries.selectAllExercises(::exercises).executeAsList()

    fun insertExercises(entity: ExercisesEntity) = database.appDatabaseQueries.insertExercises(
        entity.id ?: 0L,
        entity.name ?: "",
        entity.repeat ?: 0,
        entity.interval ?: 0,
        entity.peso ?: 0,
        entity.type ?: 0,
    )

    private fun exercises(
        id: Long,
        nome: String,
        repeat: Long,
        interval: Long,
        peso: Long,
        type: Long

    ) = ExercisesEntity(
        id,
        nome,
        repeat,
        interval,
        peso,
        type
    )
}