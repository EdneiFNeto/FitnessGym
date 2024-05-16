package com.minhasafra360.android.screens.principal

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minhasafra360.android.R
import com.minhasafra360.android.navigation.BottomNavigation
import com.minhasafra360.android.navigation.TopAppBarComponent
import com.minhasafra360.db.entity.ExercisesEntity
import com.minhasafra360.principal.PrincipalState
import com.minhasafra360.principal.TypeSerie
import com.minhasafra360.principal.fakes


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrincipalScreen(
    onNavigateToExercises: (Long?) -> Unit,
    principalState: PrincipalState
) {
    val serieTypeA = principalState.entity.filter { it.type?.toInt() == TypeSerie.TYPE_A.literal }
    val serieTypeB = principalState.entity.filter { it.type?.toInt() == TypeSerie.TYPE_B.literal }

    Scaffold(
        topBar = {
            TopAppBarComponent()
        },
        bottomBar = {
            BottomNavigation()
        }
    ) {
        ContainerComponent(serieTypeA, onNavigateToExercises, serieTypeB)
    }
}

@Composable
private fun ContainerComponent(
    serieTypeA: List<ExercisesEntity>,
    onNavigateToExercises: (Long?) -> Unit,
    serieTypeB: List<ExercisesEntity>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 18.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp),
            text = stringResource(id = R.string.app_name),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        if (serieTypeA.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                text = stringResource(id = R.string.serie_a),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            HorizontalDivider()

            LazyColumn(
                modifier = Modifier.padding(horizontal = 8.dp),
                content = {
                    items(serieTypeA) { entity ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onNavigateToExercises(entity.id) }
                                .padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(
                                text = entity.name ?: "",
                                modifier = Modifier
                                    .weight(.4f),
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = entity.repeat.toString(),
                                modifier = Modifier.weight(.1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = entity.interval.toString(),
                                modifier = Modifier.weight(.1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = entity.peso.toString(),
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

        if (serieTypeB.isNotEmpty()) {
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                text = stringResource(id = R.string.serie_b),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            HorizontalDivider()

            LazyColumn(
                content = {
                    items(serieTypeA) { entity ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(
                                text = entity.name ?: "",
                                modifier = Modifier
                                    .weight(.4f),
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = entity.repeat.toString(),
                                modifier = Modifier.weight(.1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = entity.interval.toString(),
                                modifier = Modifier.weight(.1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = entity.peso.toString(),
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
}

@Preview(showBackground = true)
@Composable
private fun PrincipalScreenPreview() {
    PrincipalScreen(
        principalState = PrincipalState(entity = fakes),
        onNavigateToExercises = {}
    )
}