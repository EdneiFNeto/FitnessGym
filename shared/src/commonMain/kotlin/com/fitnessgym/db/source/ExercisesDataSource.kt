package com.fitnessgym.db.source

import com.fitnessgym.FitnessGymDatabase
import com.fitnessgym.db.entity.ExercisesEntity

class ExercisesDataSource(private val database: FitnessGymDatabase) {
    fun selectExercises(): MutableList<ExercisesEntity> =
        database.exercisesQueries.selectExercises(::exercises).executeAsList() as MutableList<ExercisesEntity>

    fun insertExercises(entity: ExercisesEntity): Long {
        database.exercisesQueries.insertExercises(
            id = entity.id,
            entity.name,
            entity.repeat,
            entity.interval,
            entity.peso,
            entity.type,
        )

        return 1
    }

    fun updateExercises(entity: ExercisesEntity): Long {
        database.exercisesQueries.updateExercises(
            name = entity.name,
            repeat = entity.repeat,
            interval = entity.interval,
            peso = entity.peso,
            type = entity.type,
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