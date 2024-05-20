package com.minhasafra360.android.screens.exercises

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minhasafra360.ExercisesType
import com.minhasafra360.FetchStatus
import com.minhasafra360.addexercises.AddExercisesEvent
import com.minhasafra360.addexercises.AddExercisesUIState
import com.minhasafra360.android.R
import com.minhasafra360.android.utils.ButtonComponent
import com.minhasafra360.android.utils.DropDownMenuComponent
import com.minhasafra360.android.utils.OutlineTextFiledComponent
import com.minhasafra360.db.entity.ExercisesEntity

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
    uiState: AddExercisesUIState,
    handleEvent: (AddExercisesEvent) -> Unit
) {
    val addExercisesState by remember { mutableStateOf(AddExercisesState()) }

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
            handleEvent(AddExercisesEvent.OnSaveEntity(entity))
        }
    }

    if (uiState.status == FetchStatus.FAIL) {
        Toast.makeText(LocalContext.current, uiState.error, Toast.LENGTH_SHORT).show()
    }
}

private fun exercisesEntity(
    addExercisesState: AddExercisesState
): ExercisesEntity {
    val type =
        addExercisesState.list.find { it.literal == (addExercisesState.type.value ?: "1").toInt() }
    return ExercisesEntity(
        id = 0L,
        name = addExercisesState.name.value,
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
        uiState = AddExercisesUIState(),
        handleEvent = {}
    )
}