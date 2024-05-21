package com.fitnessgym.android.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fitnessgym.R

data class TopAppBarStateComponent(
    val navigation: MutableState<@Composable RowScope.() -> Unit> = mutableStateOf({}),
    val actions: MutableState<@Composable RowScope.() -> Unit> = mutableStateOf({}),
    val visibility: MutableState<Boolean> = mutableStateOf(true)
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TopAppBarComponent(
    topAppBarState: TopAppBarStateComponent
) {
    val action = topAppBarState.navigation.value
    if (topAppBarState.visibility.value) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(12.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.LightGray,
            ),
            title = {
                Column(
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = "FitnessGym", fontWeight = FontWeight.Medium,
                        fontSize = 13.sp
                    )
                    Text(
                        modifier = Modifier.offset(y = (-6).dp),
                        text = "Jordan Eagle", fontWeight = FontWeight.Bold,
                        fontSize = 19.sp,
                        color = Color.DarkGray
                    )
                }
            },
            navigationIcon = {

            },
            actions = {
                Box(
                    modifier = Modifier.padding(top = 16.dp, start = 8.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_avatar),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .border(
                                BorderStroke(2.dp, Color.LightGray),
                                CircleShape
                            )
                            .clip(CircleShape)
                    )
                }
            }
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