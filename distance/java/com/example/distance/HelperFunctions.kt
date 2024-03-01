package com.example.distance

import org.json.JSONArray


fun parseStopsFromJson(jsonData: String): List<Stop> {
    val jsonArray = JSONArray(jsonData)
    var stops = listOf<Stop>()
    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        val station = jsonObject.getString("station")
        val distance = jsonObject.getDouble("distance")
        val isVisited = jsonObject.getBoolean("isVisited")
        stops+=Stop(station, distance, isVisited)
    }
    return stops
}