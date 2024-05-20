package com.fitnessgym.android.screens.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fitnessgym.R
import com.fitnessgym.android.utils.ButtonComponent
import com.fitnessgym.android.utils.OutlineTextFiledComponent
import com.fitnessgym.db.entity.ExercisesEntity
import com.fitnessgym.principal.fakes

internal data class UpdateExercisesRouteScreenExercisesState(
    val exercises: ExercisesEntity?,
    val name: MutableState<String> = mutableStateOf(exercises?.name ?: ""),
    val repeat: MutableState<String> = mutableStateOf((exercises?.repeat ?: 0).toString()),
    val interval: MutableState<String> = mutableStateOf((exercises?.interval ?: 0).toString()),
    val peso: MutableState<String> = mutableStateOf((exercises?.peso ?: 0).toString()),
    val type: MutableState<String> = mutableStateOf((exercises?.type ?: 0).toString()),
)

@Composable
fun UpdateExercisesRouteScreen(
    id: String,
    navigationToPrincipal: () -> Unit
) {
    val exercises = fakes.last()
    val updateExercisesState by remember { mutableStateOf(UpdateExercisesRouteScreenExercisesState(exercises)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp),
            text = stringResource(id = R.string.update_exercises),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        OutlineTextFiledComponent(
            label = stringResource(id = R.string.label_exercises),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            value = updateExercisesState.name.value,
            onValueChanged = {
                updateExercisesState.name.value = it
            }
        )

        OutlineTextFiledComponent(
            label = stringResource(id = R.string.label_repeat),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number,
            value = updateExercisesState.repeat.value,
            onValueChanged = {
                updateExercisesState.repeat.value = it
            }
        )

        OutlineTextFiledComponent(
            label = stringResource(id = R.string.label_interval),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number,
            value = updateExercisesState.interval.value,
            onValueChanged = {
                updateExercisesState.interval.value = it
            }
        )

        OutlineTextFiledComponent(
            label = stringResource(id = R.string.label_peso),
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Decimal,
            value = updateExercisesState.peso.value,
            onValueChanged = {
                updateExercisesState.peso.value = it
            }
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )

        ButtonComponent(
            label = stringResource(id = R.string.label_update)
        ) {

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun UpdateExercisesRouteScreenPreview() {
    UpdateExercisesRouteScreen(
        navigationToPrincipal = {},
        id = ""
    )
}