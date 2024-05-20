package com.minhasafra360.android.screens.principal

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minhasafra360.android.R
import com.minhasafra360.android.navigation.TopAppBarStateComponent
import com.minhasafra360.principal.ExercisesPage
import com.minhasafra360.principal.PrincipalState
import com.minhasafra360.principal.exercisesPages

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrincipalScreen(
    topAppBarStatus: TopAppBarStateComponent,
    state: PrincipalState,
    onNavigateToExercises: (Int) -> Unit
) {
    topAppBarStatus.apply {
        visibility.value = true
        actions.value = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null)
            }
        }
        navigation.value = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
            }
        }
    }

    ContainerComponent(onNavigateToExercises)
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

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
        ) { page ->
            currentPage = page
            ExercicesPager(page = exercisesPages[page], onNavigateToExercises )
        }
    }
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
                                entity.id?.let {
                                    onNavigateToExercises(it.toInt())
                                }
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
        topAppBarStatus = TopAppBarStateComponent(),
        state = PrincipalState(),
        onNavigateToExercises = {}
    )
}