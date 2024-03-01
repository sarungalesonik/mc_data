package com.example.distance

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.distance.ui.theme.DistanceTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import org.json.JSONArray
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

data class Stop(val station: String, var distance: Double, var isVisited: Boolean)

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DistanceTheme {
                //For normal stop list with 10 stops
                //var jsonData = NormalStopsData()
                //For Lazy stop list with more that 10 stops
                var jsonData = lazyStopsData()

                var stops by remember {
                    mutableStateOf(
                        parseStopsFromJson(jsonData)
                    )
                }
                var unit by remember { mutableStateOf("km") }
                var progress by remember { mutableStateOf(0.0f) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    ProgressBar(progress = progress)

                    DetailBox(stops = stops, progress = progress,unit = unit)

                    ShowStopList(stops = stops,unit = unit)

                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    FloatingActionButton(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp),
                        onClick = {
                            for (i in stops.indices) {
                                stops[i].isVisited = false
                            }
                            progress = 0.0f
                        },
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        shape = RoundedCornerShape(50.dp),
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = "Reset", modifier = Modifier.size(17.dp))
                    }
                }
                BottomBtnRow(progress = progress, stops = stops, unit = unit,
                    onProgressChanged = { newProgress ->
                        progress = newProgress
                        val station_index = ((newProgress * (stops.size-1))).toInt()-1
                        stops[station_index].isVisited = true
                    },
                    onConvertUnits = {newUnit ->
                        unit = newUnit

                        if(unit=="km"){
                            for (i in stops.indices) {
                                stops[i].distance = stops[i].distance * 1.60934
                            }
                        }else{
                            for (i in stops.indices) {
                                stops[i].distance = stops[i].distance * 0.621371
                            }
                        }

                    }
                )
            }
        }
    }
}


