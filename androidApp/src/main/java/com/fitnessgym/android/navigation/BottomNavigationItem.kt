package com.fitnessgym.android.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fitnessgym.R

@Composable
fun BottomNavigationItem(
    icon: Int,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = null)
        Text(text = label)
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationItemPreview() {
    BottomNavigationItem(
        icon = R.drawable.ic_pencil,
        BottomNavItem.Home.title
    )
}