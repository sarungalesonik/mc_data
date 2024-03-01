//package com.example.mycoroutines
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.mycoroutines.ui.theme.MyCoroutinesTheme
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.collect
//import kotlinx.coroutines.flow.onEach
//import kotlinx.coroutines.launch
////
////class MainActivity : ComponentActivity() {
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContent {
////            MyCoroutinesTheme {
////                Surface(
////                    modifier = Modifier.fillMaxSize(),
////                    color = MaterialTheme.colorScheme.background
////                ) {
////                    MyApp()
////                }
////            }
////        }
////    }
////}
////
//class MyViewModel : ViewModel() {
//    private val _fetchDataState = MutableStateFlow<String?>(null)
//    val fetchDataState: StateFlow<String?> = _fetchDataState
//
//    private val _isLoading = MutableStateFlow(false)
//    val isLoading: StateFlow<Boolean> = _isLoading
//
//    fun triggerFetchData() {
//        viewModelScope.launch {
//            fetchDataFromRemote()
//        }
//    }
//    suspend fun fetchDataFromRemote() {
//        try {
//            _isLoading.value = true
//            delay(2000)
//            _fetchDataState.value = "Data fetched successfully"
//        } catch (e: Exception) {
//            // Handle error
//        } finally {
//            _isLoading.value = false
//        }
//    }
//}
//
//@Composable
//fun MyScreen(viewModel: MyViewModel) {
//    val fetchDataState: String? by viewModel.fetchDataState.collectAsState(null)
//    val isLoading: Boolean by viewModel.isLoading.collectAsState(false)
//
//    Column {
//        Button(
//            onClick =
//            {viewModel.triggerFetchData()},
//            modifier = Modifier.padding(16.dp),
//            enabled = !isLoading // Disable button when loading
//        ) {
//            Text("Fetch Data")
//        }
//
//
//
//
//        // Show loading indicator
//        if (isLoading) {
//            Text("Loading...", modifier = Modifier.padding(16.dp))
//        }
//
//        // Display the fetched data or error message
//        fetchDataState?.let {
//            Text(text = it, modifier = Modifier.padding(16.dp))
//        }
//    }
//}
//
////@Preview(showBackground = true)
////@Composable
////fun DefaultPreview() {
////    MyCoroutinesTheme {
////        MyApp()
////    }
////}
////
////@Composable
////fun MyApp() {
////    val viewModel = remember { MyViewModel() }
////    MyScreen(viewModel = viewModel)
////}
