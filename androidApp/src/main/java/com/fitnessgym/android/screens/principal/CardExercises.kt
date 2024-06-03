package com.fitnessgym.android.screens.principal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fitnessgym.ExercisesType
import com.fitnessgym.R
import com.fitnessgym.db.entity.ExercisesEntity
import com.fitnessgym.principal.fakes

@Composable
internal fun CardExercises(
    entity: ExercisesEntity,
    totalRepeat: Int,
    timer: Long,
    percent: Float,
    onClickButton: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = stringResource(R.string.progress),
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp
            )

            Spacer(modifier = Modifier.padding(vertical = 12.dp))

            Row {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(30.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        val type = if(entity.type == ExercisesType.SERIEB.literal){
                            "Serie B"
                        } else {
                            "Serie A"
                        }
                        Text(
                            text = type,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.padding(vertical = 2.dp))

                    Text(
                        text = entity.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        maxLines = 2
                    )

                    TimePesoRepeatComponent(
                        entity,
                        totalRepeat,
                        timer
                    )
                }

                PercentComponent(percent = percent)
            }

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    if (timer <= 0) onClickButton()
                }
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {

                    if (timer <= 0) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = stringResource(id = R.string.start_exercises)
                        )
                        Icon(
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .align(Alignment.CenterEnd),
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null
                        )
                    } else {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = stringResource(id = R.string.execute)
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun PercentComponent(
    percent: Float
) {
    Box(
        modifier = Modifier
            .width(90.dp)
            .height(90.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        val percentFormatted = if (percent <= 0f) "0" else
            String.format("%.1f", percent)
        Text(text = percentFormatted.plus("%"), color = Color.White)
        DrawCircle()
        CircularProgressBar(progress = percent)
    }
}

@Composable
internal fun TimePesoRepeatComponent(
    entity: ExercisesEntity,
    totalRepeat: Int,
    timer: Long
) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
    ) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_timer),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            val timeFormat = if (timer > 9) "$timer" else "0$timer"
            Text(
                modifier = Modifier.padding(1.dp),
                text = "00:$timeFormat",
                fontSize = 12.sp
            )
        }

        Row(
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_gymn),
                contentDescription = null,
                tint = Color.DarkGray,
                modifier = Modifier.size(16.dp)
            )
            Text(
                modifier = Modifier.padding(1.dp),
                text = "${entity.peso}Kg",
                fontSize = 12.sp
            )
        }

        Row(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_repeat),
                contentDescription = null,
                tint = Color.DarkGray,
                modifier = Modifier.size(16.dp)
            )
            Text(
                modifier = Modifier.padding(1.dp),
                text = "${totalRepeat}-${entity.repeat}x",
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardExercisesPreview() {
    CardExercises(
        entity = fakes.first(),
        totalRepeat = 0,
        timer = 10,
        percent = .75F,
        onClickButton = {}
    )
}