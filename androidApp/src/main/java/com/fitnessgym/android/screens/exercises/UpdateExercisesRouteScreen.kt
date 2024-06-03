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
import com.fitnessgym.FetchStatus
import com.fitnessgym.R
import com.fitnessgym.android.utils.AlertDialogComponent
import com.fitnessgym.android.utils.ButtonComponent
import com.fitnessgym.android.utils.OutlineTextFiledComponent
import com.fitnessgym.android.utils.TextButtonComponent
import com.fitnessgym.db.entity.ExercisesEntity
import com.fitnessgym.exercises.ExercisesEvent
import com.fitnessgym.exercises.ExercisesUIState

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
    id: Long,
    uiState: ExercisesUIState,
    handleEvent: (ExercisesEvent) -> Unit,
    onNavigationTo: () -> Unit
) {
    val exercises = uiState.exercises.find { it.id == id }
    val updateExercisesState by remember {
        val value = UpdateExercisesRouteScreenExercisesState(exercises)
        mutableStateOf(value)
    }

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
            val data = updateExercisesState.toMapOf()
            handleEvent(ExercisesEvent.OnUpdateExercises(data))
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        TextButtonComponent(
            label = stringResource(id = R.string.label_delete)
        ) {
            val data = updateExercisesState.toMapOf()
            handleEvent(ExercisesEvent.OnDeleteExercises(data))
        }
    }

    when (uiState.fetchStatus) {
        FetchStatus.DONE -> onNavigationTo()
        FetchStatus.FAIL -> AlertDialogComponent(
            dialogText = uiState.error ?: "",
            onConfirm = {
                handleEvent(ExercisesEvent.OnDoneFetch)
            }) {
        }

        else -> {}
    }
}

private fun UpdateExercisesRouteScreenExercisesState.toMapOf(): Map<String, String> {
    val name = this.name.value
    val repeat = this.repeat.value
    val interval = this.interval.value
    val peso = this.peso.value

    return mapOf(
        "name" to name,
        "repeat" to repeat,
        "interval" to interval,
        "peso" to peso,
    )
}

@Preview(showBackground = true)
@Composable
private fun UpdateExercisesRouteScreenPreview() {
    UpdateExercisesRouteScreen(
        id = 1,
        uiState = ExercisesUIState(),
        handleEvent = {},
        onNavigationTo = {}
    )
}