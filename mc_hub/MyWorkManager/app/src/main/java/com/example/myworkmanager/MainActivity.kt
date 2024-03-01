package com.example.myworkmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myworkmanager.ui.theme.MyWorkManagerTheme
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // Create a one-time work request with a delay of 1 minute
            val workRequest = OneTimeWorkRequestBuilder<MyWorker>().build()
            // Enqueue the work request with WorkManager
            WorkManager.getInstance(this).enqueue(workRequest)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyWorkManagerTheme {
        Greeting("Android")
    }
}