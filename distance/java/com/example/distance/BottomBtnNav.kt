package com.example.distance

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun BottomBtnRow(
    progress: Float,
    stops: List<Stop>,
    unit: String,
    onProgressChanged: (Float) -> Unit,
    onConvertUnits: (String) -> Unit) {
    val absoluteOffset = (4).dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(Modifier.absoluteOffset(y = absoluteOffset)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val newUnit = if (unit=="km") "mi" else "km"
                    onConvertUnits(newUnit)
              },
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Text(text = "Convert Units")
            }
            Button(
                onClick = {
                    val step = 1.0f / (stops.size-1)
                    val newProgress = if (progress < 1.0f) progress + step else progress
                    onProgressChanged(newProgress)
                },
                enabled = if (progress < 1.0f) true else false,
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                    disabledContainerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(text = "Next")
            }
        }
    }
}
