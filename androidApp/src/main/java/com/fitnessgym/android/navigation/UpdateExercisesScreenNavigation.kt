package com.fitnessgym.android.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fitnessgym.android.screens.exercises.UpdateExercisesRouteScreen
import com.fitnessgym.exercises.ExercisesViewModel
import org.koin.androidx.compose.getViewModel


fun NavGraphBuilder.updateExercisesScreen(
    navHostController: NavHostController
) {
    composable(
        NavigationScreen.AddExercises.route.plus("/{id}"),
        arguments = listOf(navArgument("id") { type = NavType.LongType })
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getLong("id") ?: 0L

        val viewModel: ExercisesViewModel = getViewModel()
        val uiState = viewModel.uiState.collectAsState().value

        UpdateExercisesRouteScreen(
            id = id,
            uiState = uiState,
            handleEvent = viewModel::handleEvent,
            onNavigationTo = {
                navHostController.popBackStack()
            }
        )
    }
}

internal fun NavController.navigateToUpdateExercises(id: Long?) {
    navigate(NavigationScreen.AddExercises.route.plus("/$id"))
}