package com.fitnessgym.android.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fitnessgym.R

@Composable
fun ShowDialogComponent(
    openDialogCustom: MutableState<Boolean>,
    message: String,
    handle: () -> Unit = {}
) {
    Dialog(onDismissRequest = { openDialogCustom.value = false }) {
        CustomDialogUI(
            openDialogCustom = openDialogCustom,
            message = message,
            handle = handle
        )
    }
}

@Composable
private fun CustomDialogUI(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    message: String,
    handle: () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier.background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),
            )

            Text(
                text = message,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Light
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                TextButton(onClick = {
                    openDialogCustom.value = false
                    handle()
                }) {
                    Text(
                        stringResource(R.string.confirmar),
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, bottom = 5.dp),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowDialogComponentPreview() {
    val open = remember { mutableStateOf(true) }
    ShowDialogComponent(
        message = "",
        openDialogCustom = open,
        handle = {}
    )

}