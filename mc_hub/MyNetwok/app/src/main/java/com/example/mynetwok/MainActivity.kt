package com.example.mynetwok


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mynetwok.ui.theme.MyNetwokTheme
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {

    private val viewModel: MarsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNetwokTheme {
                MarsPhotoScreen(viewModel)
            }
        }
    }

    @Composable
    fun MarsPhotoScreen(viewModel: MarsViewModel) {
        val marsPhotos = viewModel.marsPhotos.collectAsState()

        Column {
            Text(text = "Mars Photos:")
            for (photo in marsPhotos.value) {
                Text(text = "ID: ${photo.id}, Img Src: ${photo.imgSrc}")
            }
        }
    }



}
