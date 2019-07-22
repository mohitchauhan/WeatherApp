package com.android.xu.data.entities

data class WeatherForecast(val currentTemp_c : Int, val cityName : String, val days : ArrayList<Forecastday>) {
}