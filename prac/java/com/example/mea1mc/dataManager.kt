package com.example.mea1mc

import android.content.Context
import java.io.File
import com.google.gson.Gson

object dataManager {
    var data = emptyArray<Company>()
    var isLoaded = false

    fun loadData(context: Context){
        val fileInString: String =
            context.assets.open("data.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        data = gson.fromJson(fileInString, Array<Company>::class.java)
        isLoaded = true

    }
    fun appendData(company: Company){
        data += company
    }
}