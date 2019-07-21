package com.android.xu.xuapi

import com.android.xu.xuapi.entities.WeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

    @GET(" /v1/forecast.json")
    fun forecast(@Query("key") key: String, @Query("q") location : String): Call<WeatherForecast>

}