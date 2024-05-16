package com.minhasafra360.android

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.minhasafra360.android.navigation.FitnessGymNaviHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavControllerComponent(
) {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(navController = navController)
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    FitnessGymNaviHost(navController = navController)
}