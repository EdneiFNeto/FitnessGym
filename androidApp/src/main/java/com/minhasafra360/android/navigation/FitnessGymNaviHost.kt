package com.minhasafra360.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

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
        exercisesScreen(navController)
    }
}