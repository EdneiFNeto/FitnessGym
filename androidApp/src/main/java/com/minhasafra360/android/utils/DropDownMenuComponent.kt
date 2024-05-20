package com.minhasafra360.android.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DropDownMenuComponent(
    list: List<String>,
    textFieldValue: TextFieldValue,
    onSelected: (Int) -> Unit = {}
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedIndex = remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopStart)
    ) {

        OutLineTextFieldDropDownComponent(
            textFieldValue = textFieldValue,
            label = textFieldValue.text,
            trailingIcon = {
                if (list.isNotEmpty()) {
                    val icon = if (expanded.value)
                        Icons.Filled.KeyboardArrowUp
                    else Icons.Filled.KeyboardArrowDown

                    IconButton(onClick = {
                        expanded.value = !expanded.value
                    }) {
                        Icon(
                            icon,
                            null,
                            tint = Color.LightGray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                } else {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.KeyboardArrowDown,
                            null,
                            tint = Color.LightGray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            },
        )

        if (list.isNotEmpty()) {
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.fillMaxWidth()
            ) {

                list.forEachIndexed { index, s ->
                    DropdownMenuItem(
                        text = {
                            Text(text = s, color = Color.Black)
                        },
                        onClick = {
                            selectedIndex.value = index
                            expanded.value = false
                            onSelected(index)
                        })
                }
            }
        }
    }
}

@Composable
fun OutLineTextFieldDropDownComponent(
    textFieldValue: TextFieldValue,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    label: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textFieldValue,
        onValueChange = { textFieldValue.copy(text = it.text) },
        label = { Text(label ?: "", color = Color.Black, fontSize = 12.sp) },
        singleLine = true,
        placeholder = { Text(label ?: "", color = Color.Black, fontSize = 12.sp) },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        trailingIcon = trailingIcon,
        textStyle = TextStyle(
            fontSize = 12.sp, color = Color.Black
        ),
        shape = RoundedCornerShape(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun DropDownMenuComponentPreview() {
    DropDownMenuComponent(
        listOf(),
        onSelected = {},
        textFieldValue = TextFieldValue("Serie A")
    )
}