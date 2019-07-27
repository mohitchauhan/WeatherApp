package com.android.xu.data.entities

import com.android.xu.xuapi.entities.XuWeatherForecast

data class WeatherForecast(val currentTemp_c : Double, val cityName : String, val days : ArrayList<Forecastday>) {
    constructor(xuWeatherForecast: XuWeatherForecast) : this(xuWeatherForecast.current.temp_c, xuWeatherForecast.location.name, arrayListOf<Forecastday>().apply {
        xuWeatherForecast.forecast.forecastday.forEach { it -> add(Forecastday(it.date, it.date_epoch, Day(it.day.maxtemp_c)))}
    })
}