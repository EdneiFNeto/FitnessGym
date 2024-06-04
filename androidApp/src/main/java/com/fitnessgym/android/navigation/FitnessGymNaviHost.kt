package com.fitnessgym.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun FitnessGymNaviHost() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    println("currentBackStackEntry = ${currentBackStackEntry?.destination?.route}")

    NavHost(
        navController = navController,
        startDestination = NavigationScreen.SplashScree.route
    ) {
        splashScreen(navController)
        principalScreen(navController)
        addExercisesScreen(navController)
        updateExercisesScreen(navController)
    }
}