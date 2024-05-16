package com.minhasafra360.android.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.minhasafra360.android.screens.principal.PrincipalScreen
import com.minhasafra360.principal.PrincipalViewModel
import org.koin.androidx.compose.getViewModel


internal const val principaltRoute = "principal"
internal fun NavGraphBuilder.principalScreen(navHostController: NavHostController) {
    composable(principaltRoute) {

        val principalViewModel: PrincipalViewModel = getViewModel()
        val state by principalViewModel.principalState.collectAsState()

        PrincipalScreen(
            principalState = state,
            onNavigateToExercises = {
                navHostController.navigateToExercises(it)
            }
        )
    }
}

internal fun NavController.navigateToPrincipal() {
    navigate(principaltRoute)
}