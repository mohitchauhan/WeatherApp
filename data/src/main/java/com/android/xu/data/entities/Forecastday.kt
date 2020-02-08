package com.android.xu.data.entities


data class Forecastday (
	val apparentTemperatureHigh: Double,
	val apparentTemperatureHighTime: Double,
	val apparentTemperatureLow: Double,
	val apparentTemperatureLowTime: Double,
	val apparentTemperatureMax: Double,
	val apparentTemperatureMaxTime: Double,
	val apparentTemperatureMin: Double,
	val apparentTemperatureMinTime: Double,
	val cloudCover: Double,
	val dewPoint: Double,
	val humidity: Double,
	val icon: String,
	val summary: String,
	val sunriseTime: Double,
	val sunsetTime: Double,
	val temperatureHigh: Double,
	val temperatureHighTime: Int,
	val temperatureLow: Double,
	val temperatureLowTime: Double,
	val temperatureMax: Double,
	val temperatureMaxTime: Int,
	val temperatureMin: Double,
	val temperatureMinTime: Double,
	val time: Long,
	val displayDate: String,
	val windSpeed: Double
)

