package com.fitnessgym.android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fitnessgym.android.screens.splashscreen.SplashScreen

internal const val splashScreenRoute = "splash-screen"

fun NavGraphBuilder.splashScreen(
    navHostController: NavHostController
) {
    composable(splashScreenRoute) {
        SplashScreen(
            navigationToPrincipal = {
                navHostController.navigateToPrincipal()
            },
        )
    }
}