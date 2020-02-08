package com.android.xu.darkskyapi

import com.android.xu.darkskyapi.entities.DarkSkyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DarkSkyService{
    //https://api.darksky.net/forecast/9844b7c637382f10dd392c4c3deac33a/37.8267,-122.4233
    @GET(" /forecast/{key}/{location}?exclude=[minutely,hourly,alerts,flags]")
    fun forecast(@Path("key") key : String, @Path("location") location : String): Call<DarkSkyResponse>
}