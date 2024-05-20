package com.fitnessgym.android.navigation

import com.fitnessgym.R


sealed class BottomNavItem(var title: String, var icon: Int, var route: String) {
    data object Home : BottomNavItem("Home", R.drawable.ic_gymn, "home")
    data object Result : BottomNavItem("Resultado", R.drawable.ic_chart, "chart")
    data object Profile : BottomNavItem("Perfil", R.drawable.ic_profile, "profile")
}
