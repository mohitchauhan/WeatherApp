package com.android.xu.data.repositories.forecast

import com.android.xu.core.annotations.XuService
import com.android.xu.core.data.entities.Result
import com.android.xu.data.entities.WeatherForecast
import com.android.xu.location_api.LocationProvider
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetForecastRepository @Inject constructor(@XuService private val  weatherForecastSource : WeatherForecastDataSource, private val locationProvider : LocationProvider) {

    suspend fun getForecast(days : Int) : Result<WeatherForecast>{
        val location = locationProvider.getLocation();
        return weatherForecastSource.getWeatherForecast(location.toQueryParams(), days);
    }
}