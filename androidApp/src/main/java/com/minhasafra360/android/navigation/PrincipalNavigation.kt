package com.minhasafra360.android.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.minhasafra360.android.BottomNavigationState
import com.minhasafra360.android.FlatIconState
import com.minhasafra360.android.TopAppBarStateComponent
import com.minhasafra360.android.screens.ScreenFitness
import com.minhasafra360.android.screens.principal.PrincipalScreen
import com.minhasafra360.principal.PrincipalViewModel
import org.koin.androidx.compose.getViewModel


internal const val principaltRoute = "principal"
internal fun NavGraphBuilder.principalScreen(
    topAppBarStatus: TopAppBarStateComponent,
    bottomNavigationState: BottomNavigationState,
    flatIconState: FlatIconState,
    navHostController: NavHostController
) {
    composable(principaltRoute) {

        val principalViewModel: PrincipalViewModel = getViewModel()
        val state by principalViewModel.principalState.collectAsState()
        bottomNavigationState.visibility.value = true
        flatIconState.visibility.value = true

        PrincipalScreen(
            topAppBarStatus,
            state,
            onNavigateToExercises = { id ->
                navHostController.navigateToUpdateExercises(id.toLong())
            }
        )
    }
}

internal fun NavController.navigateToPrincipal() {
    navigate(principaltRoute)
}