package com.example.distance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ceil


@Composable
fun DetailBox(stops: List<Stop>, progress: Float, unit: String){
    val station_index = ((progress * (stops.size-1))).toInt()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.Center),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray,
            ),
            shape = RoundedCornerShape(5.dp),

            ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                    ){
                        Box(
                            modifier = Modifier
                                .weight(1f),
                        ){

                            Text(
                                text = "Current Stop -> Distance -> Next Stop",
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.Black,
                                style = TextStyle(fontSize = 13.sp),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )

                        }


                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                    ){
                        Box(
                            modifier = Modifier
                                .weight(1f),
                        ){
                            var progressTitle = "${stops[station_index].station}"
                            if(station_index < stops.size-1){
                                var fromDistance = ceil(stops[station_index].distance * 100) / 100
                                var toDistance = ceil(stops[station_index+1].distance * 100) / 100
                                progressTitle+=" -> ${ceil((toDistance-fromDistance) * 100) / 100} ${unit}"
                                progressTitle+=" -> ${stops[station_index+1].station}"
                            }
                            Text(
                                text = progressTitle,
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.Black,
                                style = TextStyle(fontSize = 12.sp),
                                textAlign = TextAlign.Center
                            )

                        }


                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 10.dp),
                        ){
                            var covered_distance = ceil(stops[station_index].distance* 100) / 100
                            var final_distance = ceil(stops[stops.size-1].distance * 100) / 100
                            var remaining_distance = ceil((final_distance - covered_distance) * 100) / 100
                            Text(
                                text = "Covered: ${covered_distance} ${unit}, Remaining: ${remaining_distance} ${unit}",
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.Black,
                                style = TextStyle(fontSize = 11.sp),
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }

            }
        }
    }
}