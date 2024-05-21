package com.fitnessgym.android.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fitnessgym.addexercises.AddExercisesViewModel
import com.fitnessgym.android.BottomNavigationState
import com.fitnessgym.android.FlatIconState
import com.fitnessgym.android.screens.exercises.AddExercisesRouteScreen
import org.koin.androidx.compose.getViewModel

internal const val addExercisesRoute = "add-exercises"

fun NavGraphBuilder.addExercisesScreen(
    navHostController: NavHostController
) {
    composable(
        addExercisesRoute
    ) {
        val viewModel: AddExercisesViewModel = getViewModel()
        val uiState = viewModel.uiState.collectAsState().value

        AddExercisesRouteScreen(
            uiState = uiState,
            handleEvent = viewModel::handleEvent,
            navigationToPrincipal = { navHostController.popBackStack() }
        )
    }
}

internal fun NavController.navigateToAddExercises() {
    navigate(addExercisesRoute)
}