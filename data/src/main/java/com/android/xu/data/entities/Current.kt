package com.android.xu.data.entities


data class Current(
    val apparentTemperature: Double,
    val cloudCover: Double,
    val dewPoint: Double,
    val humidity: Double,
    val icon: String,
    val summary: String,
    val temperature: Double,
    val time: Int,
    val displayDate: String,
    val windSpeed: Double
)