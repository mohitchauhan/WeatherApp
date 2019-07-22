package com.android.xu.data.repositories.forecast

import com.android.xu.core.annotations.XuService
import com.android.xu.core.data.entities.Result
import com.android.xu.data.entities.WeatherForecast
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetForecastRepository @Inject constructor(@XuService private val  weatherForecastSource : WeatherForecastDataSource) {

    suspend fun getForecast(location: String, days : Int) : Result<WeatherForecast>{
        return weatherForecastSource.getWeatherForecast(location, days);
    }
}