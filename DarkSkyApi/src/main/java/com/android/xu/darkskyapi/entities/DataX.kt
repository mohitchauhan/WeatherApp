package com.android.xu.darkskyapi.entities


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("apparentTemperature")
    val apparentTemperature: Double,
    @SerializedName("cloudCover")
    val cloudCover: Double,
    @SerializedName("dewPoint")
    val dewPoint: Double,
    @SerializedName("humidity")
    val humidity: Double,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("ozone")
    val ozone: Double,
    @SerializedName("precipIntensity")
    val precipIntensity: Double,
    @SerializedName("precipProbability")
    val precipProbability: Double,
    @SerializedName("precipType")
    val precipType: String,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("time")
    val time: Int,
    @SerializedName("uvIndex")
    val uvIndex: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("windBearing")
    val windBearing: Int,
    @SerializedName("windGust")
    val windGust: Double,
    @SerializedName("windSpeed")
    val windSpeed: Double
)