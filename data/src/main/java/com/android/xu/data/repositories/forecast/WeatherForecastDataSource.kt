package com.android.xu.data.repositories.forecast

import com.android.xu.core.data.entities.Result
import com.android.xu.core.extensions.toResult
import com.android.xu.darkskyapi.DarkSkyAPI
import com.android.xu.darkskyapi.entities.DarkSkyResponse
import com.android.xu.data.entities.WeatherForecast
import com.android.xu.data.mappers.XuForecastToWeatherForecast
import com.android.xu.data.mappers.toLambda
import retrofit2.Response
import javax.inject.Inject

class WeatherForecastDataSource @Inject constructor(
    private val api: DarkSkyAPI,
    private val weatherForecastMapper: XuForecastToWeatherForecast
) {
    suspend fun getWeatherForecast(location: String): Result<WeatherForecast> {
        return callService(location).toResult(weatherForecastMapper.toLambda());
    }

    private fun callService(
        location: String
    ): Response<DarkSkyResponse> {
        return api.forecast(location);
    }

}