package com.fitnessgym.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.fitnessgym.android.BottomNavigationState
import com.fitnessgym.android.FlatIconState

@Composable
fun FitnessGymNaviHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = splashScreenRoute
    ) {
        splashScreen(navController)
        principalScreen(navController)
        addExercisesScreen(navController)
        updateExercisesScreen(navController)
    }
}