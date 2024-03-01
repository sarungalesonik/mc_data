package com.example.mynetwok

import ApiService
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory

class MarsViewModel : ViewModel() {
    private val _marsPhotos = MutableStateFlow<List<MarsPhoto>>(emptyList())
    val marsPhotos: StateFlow<List<MarsPhoto>> = _marsPhotos

    init {
        fetchMarsPhotos()
    }

    private fun fetchMarsPhotos() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

        val service = retrofit.create(ApiService::class.java)
        viewModelScope.launch {
            try {
                val response = service.fetchMarsPhotos()
                println(response.size)
                _marsPhotos.value = response
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }
}
