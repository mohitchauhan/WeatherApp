package com.android.xu.darkskyapi.entities


import com.google.gson.annotations.SerializedName

data class DarkSkyResponse(
    @SerializedName("currently")
    val currently: Currently,
    @SerializedName("daily")
    val daily: Daily,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("timezone")
    val timezone: String
)