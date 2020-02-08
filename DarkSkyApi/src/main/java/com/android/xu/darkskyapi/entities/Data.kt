package com.android.xu.darkskyapi.entities


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("apparentTemperatureHigh")
    val apparentTemperatureHigh: Double,
    @SerializedName("apparentTemperatureHighTime")
    val apparentTemperatureHighTime: Double,
    @SerializedName("apparentTemperatureLow")
    val apparentTemperatureLow: Double,
    @SerializedName("apparentTemperatureLowTime")
    val apparentTemperatureLowTime: Double,
    @SerializedName("apparentTemperatureMax")
    val apparentTemperatureMax: Double,
    @SerializedName("apparentTemperatureMaxTime")
    val apparentTemperatureMaxTime: Double,
    @SerializedName("apparentTemperatureMin")
    val apparentTemperatureMin: Double,
    @SerializedName("apparentTemperatureMinTime")
    val apparentTemperatureMinTime: Double,
    @SerializedName("cloudCover")
    val cloudCover: Double,
    @SerializedName("dewPoint")
    val dewPoint: Double,
    @SerializedName("humidity")
    val humidity: Double,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("moonPhase")
    val moonPhase: Double,
    @SerializedName("precipIntensity")
    val precipIntensity: Double,
    @SerializedName("precipIntensityMax")
    val precipIntensityMax: Double,
    @SerializedName("precipIntensityMaxTime")
    val precipIntensityMaxTime: Int,
    @SerializedName("precipProbability")
    val precipProbability: Double,
    @SerializedName("precipType")
    val precipType: String,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("sunriseTime")
    val sunriseTime: Double,
    @SerializedName("sunsetTime")
    val sunsetTime: Double,
    @SerializedName("temperatureHigh")
    val temperatureHigh: Double,
    @SerializedName("temperatureHighTime")
    val temperatureHighTime: Int,
    @SerializedName("temperatureLow")
    val temperatureLow: Double,
    @SerializedName("temperatureLowTime")
    val temperatureLowTime: Double,
    @SerializedName("temperatureMax")
    val temperatureMax: Double,
    @SerializedName("temperatureMaxTime")
    val temperatureMaxTime: Int,
    @SerializedName("temperatureMin")
    val temperatureMin: Double,
    @SerializedName("temperatureMinTime")
    val temperatureMinTime: Double,
    @SerializedName("time")
    val time: Long,
    @SerializedName("uvIndex")
    val uvIndex: Double,
    @SerializedName("uvIndexTime")
    val uvIndexTime: Double,
    @SerializedName("visibility")
    val visibility: Double,
    @SerializedName("windBearing")
    val windBearing: Double,
    @SerializedName("windGust")
    val windGust: Double,
    @SerializedName("windGustTime")
    val windGustTime: Double,
    @SerializedName("windSpeed")
    val windSpeed: Double
)