package com.fitnessgym.android.screens.principal

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fitnessgym.ExercisesType
import com.fitnessgym.R
import com.fitnessgym.android.navigation.BottomNavigation
import com.fitnessgym.android.navigation.TopAppBarComponent
import com.fitnessgym.android.navigation.TopAppBarStateComponent
import com.fitnessgym.android.navigation.navigateToAddExercises
import com.fitnessgym.principal.PrincipalHandleEvent
import com.fitnessgym.principal.PrincipalState
import com.fitnessgym.principal.fakes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrincipalScreen(
    uiState: PrincipalState,
    handleEvent: (PrincipalHandleEvent) -> Unit,
    onNavigateToExercises: (Long) -> Unit,
    navController: NavHostController
) {
    val topAppBarStatus by remember { mutableStateOf(TopAppBarStateComponent()) }

    Scaffold(
        topBar = {
            TopAppBarComponent(topAppBarStatus)
        },
        bottomBar = {
            BottomNavigation()
        },
        floatingActionButton = {
            FloatingActionButtonComponent(onClick = {
                navController.navigateToAddExercises()
            })
        }
    ) {
        ContainerComponent(
            onNavigateToExercises = onNavigateToExercises,
            uiState = uiState,
            handleEvent = handleEvent
        )
    }
}

@Composable
private fun ContainerComponent(
    uiState: PrincipalState,
    handleEvent: (PrincipalHandleEvent) -> Unit,
    onNavigateToExercises: (Long) -> Unit
) {
    val listType = listOf(ExercisesType.SERIEA, ExercisesType.SERIEB)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        CardExercises(
            entity = uiState.currentExercises!!,
            totalRepeat = uiState.totalRepeatExecuted,
            timer = uiState.second,
            percent = uiState.percent,
            onClickButton = {
                handleEvent(PrincipalHandleEvent.OnStartTime)
            }
        )

        Text(
            modifier = Modifier.padding(top = 24.dp, start = 12.dp),
            text = stringResource(id = R.string.label_recomentation),
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            textAlign = TextAlign.Start
        )

        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 12.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            content = {
                items(uiState.entity) { entity ->
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                            .clickable { onNavigateToExercises(entity.id ?: 0) },
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent,
                        ),
                        border = BorderStroke(1.dp, Color.LightGray)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 24.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_avatar),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .size(40.dp)
                                    .border(
                                        BorderStroke(2.dp, Color.LightGray),
                                        CircleShape
                                    )
                                    .clip(CircleShape)
                            )

                            Row(
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 60.dp)
                            ) {
                                Column {
                                    Spacer(modifier = Modifier.padding(vertical = 2.dp))

                                    Text(
                                        modifier = Modifier.width(200.dp),
                                        text = entity.name ?: "",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        maxLines = 2
                                    )

                                    Row(
                                        modifier = Modifier
                                            .padding(top = 8.dp)
                                    ) {
                                        Row {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_timer),
                                                contentDescription = null,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Text(
                                                modifier = Modifier.padding(1.dp),
                                                text = "${entity.interval}sg",
                                                fontSize = 12.sp
                                            )
                                        }

                                        Row(
                                            modifier = Modifier.padding(start = 12.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_gymn),
                                                contentDescription = null,
                                                tint = Color.DarkGray,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Text(
                                                modifier = Modifier.padding(1.dp),
                                                text = "${entity.peso}Kg",
                                                fontSize = 12.sp
                                            )
                                        }

                                        Row(
                                            modifier = Modifier.padding(start = 8.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_repeat),
                                                contentDescription = null,
                                                tint = Color.DarkGray,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Text(
                                                modifier = Modifier.padding(1.dp),
                                                text = "${entity.repeat}x",
                                                fontSize = 12.sp
                                            )
                                        }
                                    }
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(30.dp)
                                    .align(Alignment.TopEnd)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(MaterialTheme.colorScheme.primary),
                                contentAlignment = Alignment.Center
                            ) {
                                val exercisesType =
                                    listType.find { type -> type.literal.toLong() == entity.type }

                                Text(
                                    text = exercisesType?.getName(entity.type?.toInt() ?: 0) ?: "",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrincipalScreenPreview() {
    PrincipalScreen(
        uiState = PrincipalState(entity = fakes),
        onNavigateToExercises = {},
        navController = rememberNavController(),
        handleEvent = {}
    )
}