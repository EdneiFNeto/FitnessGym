package com.fitnessgym.db.source

import com.fitnessgym.FitnessGymDatabase
import com.fitnessgym.db.entity.ExercisesEntity

class ExercisesDataSource(private val database: FitnessGymDatabase) {
    fun selectExercises(): MutableList<ExercisesEntity> =
        database.exercisesQueries.selectExercises(::exercises).executeAsList() as MutableList<ExercisesEntity>

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

    fun updateExercises(entity: ExercisesEntity): Long {
        database.exercisesQueries.updateExercises(
            name = entity.name ?: "",
            repeat = entity.repeat ?: 0L,
            interval = entity.interval ?: 0,
            peso = entity.peso ?: 0,
            type = entity.type ?: 0,
            id = entity.id ?: 0,
        )

        return 1
    }

    fun deleteExercises(id: Long): Long {
        database.exercisesQueries.deleteExercises(id = id)
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