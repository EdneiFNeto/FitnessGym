package com.fitnessgym.android.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun BottomNavigation() {
    val items = listOf(
        BottomNavItem.Result,
        BottomNavItem.Home,
        BottomNavItem.Profile
    )
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items.forEach { item ->
                BottomNavigationItem(
                    icon = item.icon,
                    label = item.title,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavigationPreview() {
    BottomNavigation()
}