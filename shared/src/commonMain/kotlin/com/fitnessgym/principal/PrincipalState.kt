package com.fitnessgym.principal

import com.fitnessgym.db.entity.ExercisesEntity

data class PrincipalState(
    val entity: List<ExercisesEntity> = fakes,
    val second: Long = 0,
    val totalRepeatExecuted: Int = 0,
    val percent: Float = 0f,
)

enum class TypeSerie(val literal: Int) {
    TYPE_A(1),
    TYPE_B(2)
}
