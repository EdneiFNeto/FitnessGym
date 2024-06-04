package com.fitnessgym.android.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fitnessgym.exercises.ExercisesViewModel
import com.fitnessgym.android.screens.exercises.AddExercisesRouteScreen
import org.koin.androidx.compose.getViewModel


fun NavGraphBuilder.addExercisesScreen(
    navHostController: NavHostController
) {
    composable(
        NavigationScreen.AddExercises.route
    ) {
        val viewModel: ExercisesViewModel = getViewModel()
        val uiState = viewModel.uiState.collectAsState().value

        AddExercisesRouteScreen(
            uiState = uiState,
            handleEvent = viewModel::handleEvent,
            navigationTo = { navHostController.popBackStack() }
        )
    }
}

internal fun NavController.navigateToAddExercises() {
    navigate(NavigationScreen.AddExercises.route)
}