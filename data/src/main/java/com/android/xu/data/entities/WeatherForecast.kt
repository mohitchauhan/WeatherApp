package com.android.xu.data.entities


data class WeatherForecast(val currentLocation : String, val current : Current, val days : ArrayList<Forecastday>)