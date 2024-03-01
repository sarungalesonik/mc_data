package com.example.explict_intent

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.explict_intent.SecondActivity
import com.example.explict_intent.ui.theme.Explict_IntentTheme

class MainActivity : ComponentActivity() {

    private val startSecondActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // Handle the result if needed
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Explict_IntentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    Column {
        val context= LocalContext.current
        Button(onClick = {
            val intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Go to Second Activity")
        }
    }
}
