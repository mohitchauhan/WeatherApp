package com.android.xu.data.repositories.forecast

import com.android.xu.core.data.entities.Result
import com.android.xu.core.extensions.toResult
import com.android.xu.data.entities.WeatherForecast
import com.android.xu.data.mappers.XuForecastToWeatherForecast
import com.android.xu.data.mappers.toLambda
import com.android.xu.xuapi.APIXu
import com.android.xu.xuapi.entities.XuWeatherForecast
import retrofit2.Response
import javax.inject.Inject

class WeatherForecastDataSource @Inject constructor(
    private val apiXu: APIXu,
    private val weatherForecastMapper: XuForecastToWeatherForecast
) {
    suspend fun getWeatherForecast(location: String, days: Int): Result<WeatherForecast> {
        return callService(location, days).toResult(weatherForecastMapper.toLambda());
    }

    private fun callService(
        location: String,
        days: Int
    ): Response<XuWeatherForecast> {
        return apiXu.forecast(location, days);
    }

}