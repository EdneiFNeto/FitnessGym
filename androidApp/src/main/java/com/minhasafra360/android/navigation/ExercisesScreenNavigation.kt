package com.minhasafra360.android.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.minhasafra360.android.screens.exercises.ExercisesRouteScreen

internal const val exercisesRoute = "exercises"

fun NavGraphBuilder.exercisesScreen(navHostController: NavHostController) {
    composable(
        exercisesRoute.plus("/{id}"),
        arguments = listOf(navArgument("id") { type = NavType.LongType })
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getLong("id") ?: 0
        ExercisesRouteScreen(
            id = id,
            navigationToPrincipal = {
                navHostController.navigateToPrincipal()
            },
        )
    }
}

internal fun NavController.navigateToExercises(id: Long?) {
    navigate(exercisesRoute.plus("/$id"))
}