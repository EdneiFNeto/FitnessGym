package com.minhasafra360.android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.minhasafra360.android.TopAppBarStateComponent
import com.minhasafra360.android.screens.splashscreen.SplashScreen
import org.koin.androidx.compose.getViewModel

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