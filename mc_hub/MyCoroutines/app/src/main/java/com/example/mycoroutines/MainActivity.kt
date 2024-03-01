package com.example.mycoroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycoroutines.ui.theme.MyCoroutinesTheme
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCoroutinesTheme {
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



class MyViewModel : ViewModel() {
    private lateinit var job: Job
    private val _fetchDataState = MutableStateFlow<String?>(null)
    val fetchDataState: StateFlow<String?> = _fetchDataState

    fun fetchDataFromRemote() {
       job= viewModelScope.launch {
            delay(2000)
            _fetchDataState.value = "Data fetched successfully"
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}

@Composable
fun MyScreen(viewModel: MyViewModel) {
    val fetchDataState: String? by viewModel.fetchDataState.collectAsState(null)

    Column {
        Button(
            onClick = { viewModel.fetchDataFromRemote() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Fetch Data")
        }

        // Display the message
        fetchDataState?.let {
            Text(text = it, modifier = Modifier.padding(16.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyCoroutinesTheme {
        MyApp()
    }
}

@Composable
fun MyApp() {
    val viewModel = remember { MyViewModel() }
    MyScreen(viewModel = viewModel)
}
