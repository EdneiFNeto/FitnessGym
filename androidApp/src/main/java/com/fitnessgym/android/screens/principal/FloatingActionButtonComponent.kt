package com.fitnessgym.android.screens.principal

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
internal fun FloatingActionButtonComponent(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        contentColor = Color.White,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}