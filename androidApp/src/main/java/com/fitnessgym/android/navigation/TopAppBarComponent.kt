package com.fitnessgym.android.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.fitnessgym.R

data class TopAppBarStateComponent(
    val navigation: MutableState<@Composable RowScope.() -> Unit> = mutableStateOf({}),
    val actions: MutableState<@Composable RowScope.() -> Unit> = mutableStateOf({}),
    val visibility: MutableState<Boolean> = mutableStateOf(false)
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TopAppBarComponent(
    topAppBarState: TopAppBarStateComponent
) {
    val action = topAppBarState.navigation.value
    if (topAppBarState.visibility.value) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(
                    stringResource(id = R.string.app_name),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            },
            navigationIcon = { action },
            actions = { topAppBarState.actions.value }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TopAppBarComponentPreview() {
    val topAppBarStateComponent = TopAppBarStateComponent()
    topAppBarStateComponent.apply {
        visibility.value = true
        actions.value = {
            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
        }
    }

    TopAppBarComponent(topAppBarState = topAppBarStateComponent)
}