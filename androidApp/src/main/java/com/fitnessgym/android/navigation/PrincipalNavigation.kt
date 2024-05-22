package com.fitnessgym.android.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fitnessgym.android.screens.principal.PrincipalScreen
import com.fitnessgym.principal.PrincipalViewModel
import org.koin.androidx.compose.getViewModel


internal const val principaltRoute = "principal"
internal fun NavGraphBuilder.principalScreen(
    navHostController: NavHostController
) {
    composable(principaltRoute) {

        val principalViewModel: PrincipalViewModel = getViewModel()
        val state by principalViewModel.uiState.collectAsState()

        PrincipalScreen(
            uiState = state,
            onNavigateToExercises = { id ->
                navHostController.navigateToUpdateExercises(id)
            },
            navController = navHostController,
            handleEvent = principalViewModel::handleEvent
        )
    }
}

internal fun NavController.navigateToPrincipal() {
    navigate(principaltRoute)
}