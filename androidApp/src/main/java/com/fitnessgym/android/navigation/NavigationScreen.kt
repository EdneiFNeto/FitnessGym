package com.fitnessgym.android.navigation

sealed class NavigationScreen(val route: String) {
    data object SplashScree: NavigationScreen("SplashScree")
    data object Principal: NavigationScreen("Principal")
    data object AddExercises: NavigationScreen("AddExercises")
    data object UpdateExercises: NavigationScreen("UpdateExercises")
}