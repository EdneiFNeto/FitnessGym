package com.fitnessgym.principal

import com.fitnessgym.db.entity.ExercisesEntity

data class PrincipalState(
    val entity: MutableList<ExercisesEntity> = arrayListOf(),
    val second: Long = 0,
    val totalRepeatExecuted: Int = 0,
    val percent: Float = 0f
)

enum class TypeSeries(val literal: Int) {
    TYPE_A(1),
    TYPE_B(2)
}
