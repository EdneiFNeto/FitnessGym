package com.fitnessgym.android.screens.principal

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fitnessgym.R
import com.fitnessgym.android.navigation.BottomNavigation
import com.fitnessgym.android.navigation.TopAppBarComponent
import com.fitnessgym.android.navigation.TopAppBarStateComponent
import com.fitnessgym.android.navigation.navigateToAddExercises
import com.fitnessgym.principal.ExercisesPage
import com.fitnessgym.principal.PrincipalState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrincipalScreen(
    state: PrincipalState,
    onNavigateToExercises: (Int) -> Unit,
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
        ContainerComponent(onNavigateToExercises)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ContainerComponent(
    onNavigateToExercises: (Int) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    var currentPage by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = "Progress",
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp
                )

                Spacer(modifier = Modifier.padding(vertical = 12.dp))

                Row {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {

                        Box(
                            modifier = Modifier
                                .width(80.dp)
                                .height(30.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Serie A",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.padding(vertical = 2.dp))

                        Text(
                            text = "Puxada Aberta",
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp,
                            maxLines = 1
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
                                    text = "30sg",
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
                                    text = "10Kg",
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
                                    text = "3x",
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .width(90.dp)
                            .height(90.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "12%", color = Color.White)
                        DrawCircle()
                    }
                }

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp)
                        .align(Alignment.CenterHorizontally),
                    onClick = { }) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Next exercise"
                        )
                        Icon(
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .align(Alignment.CenterEnd),
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DrawCircle() {
    Box(
        modifier = Modifier
            .width(60.dp)
            .height(60.dp)
            .border(
                BorderStroke(2.dp, Color.LightGray),
                CircleShape
            )
            .clip(CircleShape)
    )
}

@Composable
fun ExercicesPager(
    page: ExercisesPage,
    onNavigateToExercises: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 8.dp),
            text = page.title.uppercase(),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        LazyColumn(
            content = {
                items(page.exercises) { entity ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                            .clickable {
                            },
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            text = entity.name ?: "",
                            modifier = Modifier
                                .weight(.3f),
                            textAlign = TextAlign.Start,
                            maxLines = 1
                        )
                        Text(
                            text = "${entity.repeat}x",
                            modifier = Modifier.weight(.1f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "${entity.interval}seg",
                            modifier = Modifier.weight(.2f),
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        Text(
                            text = "${entity.peso}Kg",
                            modifier = Modifier.weight(.2f),
                            textAlign = TextAlign.Center
                        )
                        Icon(
                            modifier = Modifier.weight(.1f),
                            painter = painterResource(id = R.drawable.ic_pencil),
                            contentDescription = null
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrincipalScreenPreview() {
    PrincipalScreen(
        state = PrincipalState(),
        onNavigateToExercises = {},
        navController = rememberNavController()
    )
}