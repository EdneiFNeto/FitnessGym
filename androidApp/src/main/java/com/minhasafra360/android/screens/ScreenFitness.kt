package com.minhasafra360.android.screens

sealed class ScreenFitness(private val param: String? = null) {
    data object AddExercisesScreen : ScreenFitness()
    data class UpdateExercisesScreen(val id: String) : ScreenFitness(id)
}