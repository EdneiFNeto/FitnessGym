package com.fitnessgym.db.source

import com.fitnessgym.FitnessGymDatabase
import com.fitnessgym.db.entity.ExercisesEntity

class ExercisesDataSource(private val database: FitnessGymDatabase) {
    fun selectExercises(): List<ExercisesEntity> =
        database.exercisesQueries.selectExercises(::exercises).executeAsList()

    fun insertExercises(entity: ExercisesEntity): Long {
        database.exercisesQueries.insertExercises(
            id = entity.id,
            entity.name ?: "",
            entity.repeat ?: 0,
            entity.interval ?: 0,
            entity.peso ?: 0,
            entity.type ?: 0,
        )

        return 1
    }

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