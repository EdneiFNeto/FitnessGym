package com.fitnessgym

sealed class ExercisesType(val literal: Int) {
    data object SERIEA: ExercisesType(1)
    data object SERIEB: ExercisesType(2)

    fun getName(literal: Int): String = when(literal) {
        SERIEA.literal -> "Serie A"
        SERIEB.literal -> "Serie B"
        else -> ""
    }
}

