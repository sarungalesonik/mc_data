package com.example.distance

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ceil

@Composable
fun ShowStopList(stops: List<Stop>, unit: String){

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 30.dp)
                .padding(bottom = 10.dp)
                .fillMaxHeight()
                .align(Alignment.TopCenter)

        ) {
            if (stops.size > 10) {
                LazyColumn {
                    itemsIndexed(stops) { index , stop ->
                        showStopRow(stop = stop, stopSize = stops.size, index = index, unit = unit)
                    }
                }
            } else {
                Column {
                    stops.forEachIndexed { index, stop ->
                        showStopRow(stop = stop, stopSize = stops.size, index = index, unit = unit)
                    }
                }
            }
        }
    }
}
@Composable
fun showStopRow(stop: Stop, stopSize: Int, index: Int, unit: String){

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .padding(horizontal = 13.dp),
            ){
                Text(
                    text = "${(ceil(stop.distance* 100) / 100)} ${unit}",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black,
                    textAlign = TextAlign.End,
                    style = TextStyle(fontSize = 11.sp)
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .drawWithContent {
                        drawContent()
                        if (index + 1 < stopSize) {
                            drawRect(
                                color = if (stop.isVisited == true) Color.Green else Color.Black,
                                size = Size(1.dp.toPx(), size.height),
                                topLeft = Offset(0f, 50f)
                            )
                        }
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "${stop.station}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                    , // Fixed width of 50dp for the Text
                    color = Color.Black,
                    style = TextStyle(fontSize = 13.sp)

                )
                /*
                Divider(
                    color = Color.Black,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth().padding(top = 60.dp) // Add top padding to position the Divider
                )
                 */
            }
        }
    }

}

