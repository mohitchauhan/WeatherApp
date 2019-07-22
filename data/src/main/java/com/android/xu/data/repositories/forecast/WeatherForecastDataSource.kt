package com.android.xu.data.repositories.forecast

import com.android.xu.core.data.entities.Result
import com.android.xu.core.extensions.toResult
import com.android.xu.data.entities.WeatherForecast
import com.android.xu.data.mappers.XuForecastToWeatherForecast
import com.android.xu.data.mappers.toLambda
import com.android.xu.xuapi.APIXu
import javax.inject.Inject

class WeatherForecastDataSource @Inject constructor(private val apiXu: APIXu, private  val  weatherForecastMapper: XuForecastToWeatherForecast) {
    suspend fun getWeatherForecast(location: String, days : Int) : Result<WeatherForecast>{
        return apiXu.weatherService().forecast(location, days).execute().toResult(weatherForecastMapper.toLambda());
    }
}