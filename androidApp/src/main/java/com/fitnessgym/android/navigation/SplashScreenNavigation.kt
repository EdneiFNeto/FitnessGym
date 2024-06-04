package com.fitnessgym.android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fitnessgym.android.screens.splashscreen.SplashScreen


fun NavGraphBuilder.splashScreen(
    navHostController: NavHostController
) {
    composable(NavigationScreen.SplashScree.route) {
        SplashScreen(
            navigationToPrincipal = {
                navHostController.navigateToPrincipal()
            },
        )
    }
}