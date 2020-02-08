package com.android.xu.data.mappers

import com.android.xu.darkskyapi.entities.DarkSkyResponse
import com.android.xu.data.entities.Current
import com.android.xu.data.entities.Forecastday
import com.android.xu.data.entities.WeatherForecast
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class XuForecastToWeatherForecast @Inject constructor() : Mapper<DarkSkyResponse, WeatherForecast> {

    private val OUTPUT_DATE_FORMAT = "EEEE"
    private val  outputFormat = SimpleDateFormat(OUTPUT_DATE_FORMAT, Locale.getDefault());

    override suspend fun map(from: DarkSkyResponse): WeatherForecast{
        val dayList = arrayListOf<Forecastday>()
        from.daily.data.forEach {
            val displayDate = outputFormat.format(Date(it.time*1000));
            val add = dayList.add(
                Forecastday(
                    it.apparentTemperatureHigh,
                    it.apparentTemperatureHigh,
                    it.apparentTemperatureLow,
                    it.apparentTemperatureLowTime,
                    it.apparentTemperatureMax,
                    it.apparentTemperatureMaxTime,
                    it.apparentTemperatureMin,
                    it.apparentTemperatureMinTime,
                    it.cloudCover,
                    it.dewPoint,
                    it.humidity,
                    it.icon,
                    it.summary,
                    it.sunriseTime,
                    it.sunsetTime,
                    it.temperatureHigh,
                    it.temperatureHighTime,
                    it.temperatureLow,
                    it.temperatureLowTime,
                    it.temperatureMax,
                    it.temperatureMaxTime,
                    it.temperatureMin,
                    it.temperatureMinTime,
                    it.time,
                    displayDate,
                    it.windSpeed
                )
            )
        }
        val displayDate = outputFormat.format(Date(from.currently.time*1000.toLong()));
        val current = Current(
            from.currently.apparentTemperature,
            from.currently.cloudCover,
            from.currently.dewPoint,
            from.currently.humidity,
            from.currently.icon,
            from.currently.summary,
            from.currently.temperature,
            from.currently.time,
            displayDate,
            from.currently.windSpeed
        )
        return WeatherForecast(from.timezone, current, dayList);
    }
}