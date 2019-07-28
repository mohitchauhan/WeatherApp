package com.android.xu.data.mappers

import com.android.xu.core.extensions.getDay
import com.android.xu.data.entities.Day
import com.android.xu.data.entities.Forecastday
import com.android.xu.data.entities.WeatherForecast
import com.android.xu.xuapi.entities.XuWeatherForecast
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class XuForecastToWeatherForecast @Inject constructor() : Mapper<XuWeatherForecast, WeatherForecast> {

    private val INPUT_DATE_FORMAT = "yyyy-mm-dd"
    private val OUTPUT_DATE_FORMAT = "EEEE"
    private val  outputFormat = SimpleDateFormat(OUTPUT_DATE_FORMAT, Locale.getDefault());

    override suspend fun map(from: XuWeatherForecast): WeatherForecast{
        val dayList = arrayListOf<Forecastday>()
        from.forecast.forecastday.forEach { it -> dayList.add(Forecastday(outputFormat.getDay(it.date, INPUT_DATE_FORMAT)
            , it.date_epoch, Day(it.day.maxtemp_c)))}
        return WeatherForecast(from.current.temp_c, from.location.name, dayList);
    }
}