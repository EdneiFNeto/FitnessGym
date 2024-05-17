package com.minhasafra360.android

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.minhasafra360.android.navigation.FitnessGymNaviHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavControllerComponent(
    topAppBarStatus: TopAppBarStateComponent,
    bottomNavigationState: BottomNavigationState,
    flatIconState: FlatIconState,
    navController: NavHostController
) {

    Scaffold {
        AppNavHost(
            topAppBarStatus,
            bottomNavigationState,
            flatIconState,
            navController
        )
    }
}

@Composable
fun AppNavHost(
    topAppBarStatus: TopAppBarStateComponent,
    bottomNavigationState: BottomNavigationState,
    flatIconState: FlatIconState,
    navController: NavHostController,
) {
    FitnessGymNaviHost(
        topAppBarStatus,
        bottomNavigationState,
        flatIconState,
        navController
    )
}