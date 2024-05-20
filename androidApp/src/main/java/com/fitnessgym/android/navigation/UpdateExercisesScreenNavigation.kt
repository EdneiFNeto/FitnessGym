package com.fitnessgym.android.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fitnessgym.android.BottomNavigationState
import com.fitnessgym.android.FlatIconState
import com.fitnessgym.android.screens.exercises.UpdateExercisesRouteScreen

internal const val updateExercisesRoute = "update-exercises"

fun NavGraphBuilder.updateExercisesScreen(
    topAppBarStatus: TopAppBarStateComponent,
    bottomNavigationState: BottomNavigationState,
    flatIconState: FlatIconState,
    navHostController: NavHostController
) {
    composable(
        updateExercisesRoute.plus("/{id}"),
        arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id") ?: "0"
        topAppBarStatus.apply {
            actions.value = {}
            navigation.value = {
                IconButton(onClick = {
                    navHostController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }

        bottomNavigationState.visibility.value = false
        flatIconState.visibility.value = false

        UpdateExercisesRouteScreen(
            id = id,
            navigationToPrincipal = {
                navHostController.navigateToPrincipal()
            },
        )
    }
}

internal fun NavController.navigateToUpdateExercises(id: Long?) {
    navigate(updateExercisesRoute.plus("/$id"))
}