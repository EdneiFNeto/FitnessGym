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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fitnessgym.ExercisesType
import com.fitnessgym.FetchStatus.*
import com.fitnessgym.exercises.ExercisesEvent
import com.fitnessgym.exercises.ExercisesUIState
import com.fitnessgym.R
import com.fitnessgym.android.utils.AlertDialogComponent
import com.fitnessgym.android.utils.ButtonComponent
import com.fitnessgym.android.utils.DropDownMenuComponent
import com.fitnessgym.android.utils.OutlineTextFiledComponent
import com.fitnessgym.db.entity.ExercisesEntity

internal data class AddExercisesState(
    val name: MutableState<String?> = mutableStateOf(null),
    val repeat: MutableState<String?> = mutableStateOf(null),
    val interval: MutableState<String?> = mutableStateOf(null),
    val peso: MutableState<String?> = mutableStateOf(null),
    val type: MutableState<String?> = mutableStateOf(null),
    val list: List<ExercisesType> = listOf(ExercisesType.SERIEA, ExercisesType.SERIEB),
    val textType: MutableState<TextFieldValue> = mutableStateOf(
        TextFieldValue(
            list.first().getName(1)
        )
    ),
)

@Composable
fun AddExercisesRouteScreen(
    uiState: ExercisesUIState,
    handleEvent: (ExercisesEvent) -> Unit,
    navigationTo: () -> Unit
) {
    val addExercisesState by remember { mutableStateOf(AddExercisesState()) }
    val open = remember { mutableStateOf(false) }

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
            text = stringResource(id = R.string.add_exercises),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        OutlineTextFiledComponent(
            label = stringResource(id = R.string.label_exercises),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            value = addExercisesState.name.value ?: "",
            onValueChanged = {
                addExercisesState.name.value = it
            }
        )

        OutlineTextFiledComponent(
            label = stringResource(id = R.string.label_repeat),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number,
            value = addExercisesState.repeat.value ?: "",
            onValueChanged = {
                addExercisesState.repeat.value = it
            }
        )

        OutlineTextFiledComponent(
            label = stringResource(id = R.string.label_interval),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number,
            value = addExercisesState.interval.value ?: "",
            onValueChanged = {
                addExercisesState.interval.value = it
            }
        )

        OutlineTextFiledComponent(
            label = stringResource(id = R.string.label_peso),
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Decimal,
            value = addExercisesState.peso.value ?: "",
            onValueChanged = {
                addExercisesState.peso.value = it
            }
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        DropDownMenuComponent(
            textFieldValue = addExercisesState.textType.value,
            list = addExercisesState.list.map { it.getName(it.literal) },
            onSelected = {
                val index = it
                val type = addExercisesState.list[it]
                addExercisesState.textType.value = TextFieldValue(type.getName(index + 1))
            }
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )

        ButtonComponent(
            label = stringResource(id = R.string.label_cad)
        ) {
            val entity = exercisesEntity(addExercisesState)
            handleEvent(ExercisesEvent.OnAddExercises(entity))
        }
    }

    when (uiState.fetchStatus) {
        NONE -> {}
        DONE -> navigationTo()
        FAIL -> {
            AlertDialogComponent(
                dialogText = uiState.error ?: "",
                onDismiss = {
                    handleEvent(ExercisesEvent.OnDoneFetch)
                },
            )
        }
    }
}

private fun exercisesEntity(
    addExercisesState: AddExercisesState
): ExercisesEntity {
    val type =
        addExercisesState.list.find { it.literal == (addExercisesState.type.value ?: "1").toInt() }
    return ExercisesEntity(
        id = null,
        name = addExercisesState.name.value ?: "",
        repeat = (addExercisesState.repeat.value ?: "0").toLong(),
        interval = (addExercisesState.interval.value ?: "0").toLong(),
        peso = (addExercisesState.peso.value ?: "0").toLong(),
        type = (type?.literal ?: 0).toLong()
    )
}


@Preview(showBackground = true)
@Composable
fun ExercisesRouteScreenPreview() {
    AddExercisesRouteScreen(
        uiState = ExercisesUIState(),
        handleEvent = {},
        navigationTo = {}
    )
}