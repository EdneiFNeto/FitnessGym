package com.fitnessgym.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.fitnessgym.android.BottomNavigationState
import com.fitnessgym.android.FlatIconState

@Composable
fun FitnessGymNaviHost(
    topAppBarStatus: TopAppBarStateComponent,
    bottomNavigationState: BottomNavigationState,
    flatIconState: FlatIconState,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = splashScreenRoute
    ) {
        splashScreen(navController)
        principalScreen(topAppBarStatus, bottomNavigationState, flatIconState, navController)
        addExercisesScreen(topAppBarStatus, bottomNavigationState, flatIconState, navController)
        updateExercisesScreen(topAppBarStatus, bottomNavigationState, flatIconState, navController)
    }
}