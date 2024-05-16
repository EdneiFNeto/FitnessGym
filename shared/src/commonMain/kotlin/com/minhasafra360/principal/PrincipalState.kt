package com.minhasafra360.principal

import com.minhasafra360.db.entity.ExercisesEntity

data class PrincipalState(
    val entity: List<ExercisesEntity> = mutableListOf()
)

enum class TypeSerie(val literal: Int) {
    TYPE_A(1),
    TYPE_B(2)
}
