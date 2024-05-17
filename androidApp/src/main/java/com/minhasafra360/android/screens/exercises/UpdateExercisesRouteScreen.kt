package com.minhasafra360.android.screens.exercises

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
import com.minhasafra360.android.BottomNavigationState
import com.minhasafra360.android.FlatIconState
import com.minhasafra360.android.R
import com.minhasafra360.android.TopAppBarStateComponent
import com.minhasafra360.android.utils.ButtonComponent
import com.minhasafra360.android.utils.OutlineTextFiledComponent

@Composable
fun UpdateExercisesRouteScreen(
    id: String,
    navigationToPrincipal: () -> Unit
) {
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
            onValueChanged = {}
        )

        OutlineTextFiledComponent(
            label = stringResource(id = R.string.label_series),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number,
            onValueChanged = {}
        )

        OutlineTextFiledComponent(
            label = stringResource(id = R.string.label_repeat),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number,
            onValueChanged = {}
        )

        OutlineTextFiledComponent(
            label = stringResource(id = R.string.label_interval),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number,
            onValueChanged = {}
        )

        OutlineTextFiledComponent(
            label = stringResource(id = R.string.label_peso),
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Decimal,
            onValueChanged = {}
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )

        ButtonComponent(
            label = stringResource(id = R.string.label_cad)
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