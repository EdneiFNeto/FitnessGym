package com.fitnessgym.android

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.fitnessgym.android.navigation.FitnessGymNaviHost
import com.fitnessgym.android.navigation.TopAppBarStateComponent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavControllerComponent() {
    Scaffold {
        FitnessGymNaviHost()
    }
}