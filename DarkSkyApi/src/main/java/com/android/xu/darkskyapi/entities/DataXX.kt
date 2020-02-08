package com.android.xu.darkskyapi.entities


import com.google.gson.annotations.SerializedName

data class DataXX(
    @SerializedName("precipIntensity")
    val precipIntensity: Double,
    @SerializedName("precipIntensityError")
    val precipIntensityError: Double,
    @SerializedName("precipProbability")
    val precipProbability: Double,
    @SerializedName("precipType")
    val precipType: String,
    @SerializedName("time")
    val time: Int
)