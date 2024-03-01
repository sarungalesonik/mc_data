package com.example.distance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBar(progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(50.dp)
                .align(Alignment.Center),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray,
            ),
            shape = RoundedCornerShape(5.dp),

            ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                LinearProgressIndicator(
                    progress = progress,
                    color = Color.Black,
                    trackColor = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(5.dp)
                )
            }
        }
    }
}