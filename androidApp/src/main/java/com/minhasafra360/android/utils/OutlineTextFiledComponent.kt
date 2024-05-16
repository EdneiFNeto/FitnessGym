package com.minhasafra360.android.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OutlineTextFiledComponent(
    label: String,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        value = "",
        label = {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.secondary
            )
        },
        placeholder = {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.secondary
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        onValueChange = onValueChanged
    )
}

@Preview
@Composable
fun OutlineTextFiledComponentPreview() {
    OutlineTextFiledComponent(
        label = "E-mail",
        imeAction = ImeAction.Go,
        keyboardType = KeyboardType.Text,
        onValueChanged = {}
    )
}