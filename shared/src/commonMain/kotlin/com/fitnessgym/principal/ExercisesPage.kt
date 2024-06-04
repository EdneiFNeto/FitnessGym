package com.fitnessgym.principal

import com.fitnessgym.db.entity.ExercisesEntity

data class ExercisesPage(
    val title: String,
    val stepNumber: Int,
    val exercises: List<ExercisesEntity>
)

val exercisesPages = listOf(
    ExercisesPage(
        title = "Série A",
        stepNumber = 1,
        exercises = fakes.filter { it.type?.toInt() == TypeSeries.TYPE_A.literal }
    ),
    ExercisesPage(
        title = "Série B",
        stepNumber = 4,
        exercises = fakes.filter { it.type?.toInt() == TypeSeries.TYPE_B.literal }
    )
)

