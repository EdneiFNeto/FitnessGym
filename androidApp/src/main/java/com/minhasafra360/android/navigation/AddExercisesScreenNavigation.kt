package com.minhasafra360.android.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.minhasafra360.android.BottomNavigationState
import com.minhasafra360.android.FlatIconState
import com.minhasafra360.android.TopAppBarStateComponent
import com.minhasafra360.android.screens.exercises.AddExercisesRouteScreen

internal const val addExercisesRoute = "add-exercises"

fun NavGraphBuilder.addExercisesScreen(
    topAppBarStatus: TopAppBarStateComponent,
    bottomNavigationState: BottomNavigationState,
    flatIconState: FlatIconState,
    navHostController: NavHostController
) {
    composable(
        addExercisesRoute
    ) {
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

        AddExercisesRouteScreen(
            navigationToPrincipal = {},
        )
    }
}

internal fun NavController.navigateToAddExercises() {
    navigate(addExercisesRoute)
}