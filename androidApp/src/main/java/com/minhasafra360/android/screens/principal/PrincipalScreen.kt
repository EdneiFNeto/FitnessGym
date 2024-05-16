package com.minhasafra360.android.screens.principal

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.minhasafra360.android.navigation.BottomNavigation
import com.minhasafra360.android.navigation.TopAppBarComponent
import com.minhasafra360.principal.ExercisesPage
import com.minhasafra360.principal.PrincipalState
import com.minhasafra360.principal.exercisesPages
import com.minhasafra360.principal.fakes


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrincipalScreen(
    onNavigateToExercises: (Long?) -> Unit,
    principalState: PrincipalState
) {
    Scaffold(
        topBar = {
            TopAppBarComponent()
        },
        bottomBar = {
            BottomNavigation()
        }
    ) {
        ContainerComponent()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ContainerComponent() {
    val pagerState = rememberPagerState(pageCount = { 2 })
    var currentPage by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 18.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
        ) { page ->
            Log.d("principalScreen", "ContainerComponent: page $page")
            currentPage = page
            ExercicesPager(page = exercisesPages[page])
        }
    }
}


@Composable
fun ExercicesPager(
    page: ExercisesPage
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 8.dp),
            text = page.title.uppercase(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        LazyColumn(
            content = {
                items(page.exercises) { entity ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
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
        principalState = PrincipalState(entity = fakes),
        onNavigateToExercises = {}
    )
}