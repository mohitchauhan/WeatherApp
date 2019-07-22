package com.android.xu.data.mappers

import com.android.xu.data.entities.Day
import com.android.xu.data.entities.Forecastday
import com.android.xu.data.entities.WeatherForecast
import com.android.xu.xuapi.entities.XuWeatherForecast
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class XuForecastToWeatherForecast @Inject constructor() : Mapper<XuWeatherForecast, WeatherForecast> {
    override suspend fun map(from: XuWeatherForecast): WeatherForecast{
        val dayList = arrayListOf<Forecastday>()
        from.forecast.forecastday.forEach { it -> dayList.add(Forecastday(it.date, it.date_epoch, Day(it.day.maxtemp_c)))}
        return WeatherForecast(from.current.temp_c, from.location.name, dayList);
    }
}