package com.android.app.weather.data

import com.android.xu.data.entities.WeatherForecast
import com.android.xu.data.mappers.XuForecastToWeatherForecast
import com.android.xu.xuapi.entities.Forecast
import com.android.xu.xuapi.entities.XuWeatherForecast
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking

object TestData{

    val mock4DayResponse = "\n" +
            "{\"location\":{\"name\":\"Gentilly\",\"region\":\"Ile-de-France\",\"country\":\"France\",\"lat\":48.86,\"lon\":2.35,\"tz_id\":\"Europe/Paris\",\"localtime_epoch\":1564035047,\"localtime\":\"2019-07-25 8:10\"},\"current\":{\"last_updated_epoch\":1564034414,\"last_updated\":\"2019-07-25 08:00\",\"temp_c\":26.0,\"temp_f\":78.8,\"is_day\":1,\"condition\":{\"text\":\"Sunny\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/113.png\",\"code\":1000},\"wind_mph\":3.8,\"wind_kph\":6.1,\"wind_degree\":200,\"wind_dir\":\"SSW\",\"pressure_mb\":1013.0,\"pressure_in\":30.4,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":58,\"cloud\":0,\"feelslike_c\":27.1,\"feelslike_f\":80.7,\"vis_km\":10.0,\"vis_miles\":6.0,\"uv\":7.0,\"gust_mph\":6.7,\"gust_kph\":10.8},\"forecast\":{\"forecastday\":[{\"date\":\"2019-07-25\",\"date_epoch\":1564012800,\"day\":{\"maxtemp_c\":40.9,\"maxtemp_f\":105.6,\"mintemp_c\":26.7,\"mintemp_f\":80.1,\"avgtemp_c\":33.3,\"avgtemp_f\":91.9,\"maxwind_mph\":8.7,\"maxwind_kph\":14.0,\"totalprecip_mm\":0.7,\"totalprecip_in\":0.03,\"avgvis_km\":10.0,\"avgvis_miles\":6.0,\"avghumidity\":31.0,\"condition\":{\"text\":\"Patchy rain possible\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/176.png\",\"code\":1063},\"uv\":7.5},\"astro\":{\"sunrise\":\"06:14 AM\",\"sunset\":\"09:39 PM\",\"moonrise\":\"01:12 AM\",\"moonset\":\"02:50 PM\"}},{\"date\":\"2019-07-26\",\"date_epoch\":1564099200,\"day\":{\"maxtemp_c\":30.6,\"maxtemp_f\":87.1,\"mintemp_c\":23.1,\"mintemp_f\":73.6,\"avgtemp_c\":27.5,\"avgtemp_f\":81.4,\"maxwind_mph\":15.7,\"maxwind_kph\":25.2,\"totalprecip_mm\":5.3,\"totalprecip_in\":0.21,\"avgvis_km\":9.6,\"avgvis_miles\":5.0,\"avghumidity\":52.0,\"condition\":{\"text\":\"Moderate or heavy rain shower\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/356.png\",\"code\":1243},\"uv\":7.4},\"astro\":{\"sunrise\":\"06:15 AM\",\"sunset\":\"09:38 PM\",\"moonrise\":\"01:35 AM\",\"moonset\":\"03:58 PM\"}},{\"date\":\"2019-07-27\",\"date_epoch\":1564185600,\"day\":{\"maxtemp_c\":22.0,\"maxtemp_f\":71.6,\"mintemp_c\":17.9,\"mintemp_f\":64.2,\"avgtemp_c\":19.4,\"avgtemp_f\":66.9,\"maxwind_mph\":10.1,\"maxwind_kph\":16.2,\"totalprecip_mm\":15.0,\"totalprecip_in\":0.59,\"avgvis_km\":8.3,\"avgvis_miles\":5.0,\"avghumidity\":68.0,\"condition\":{\"text\":\"Light rain\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/296.png\",\"code\":1183},\"uv\":2.4},\"astro\":{\"sunrise\":\"06:17 AM\",\"sunset\":\"09:37 PM\",\"moonrise\":\"02:01 AM\",\"moonset\":\"05:09 PM\"}},{\"date\":\"2019-07-28\",\"date_epoch\":1564272000,\"day\":{\"maxtemp_c\":24.5,\"maxtemp_f\":76.1,\"mintemp_c\":16.6,\"mintemp_f\":61.9,\"avgtemp_c\":20.1,\"avgtemp_f\":68.2,\"maxwind_mph\":13.6,\"maxwind_kph\":22.0,\"totalprecip_mm\":0.5,\"totalprecip_in\":0.02,\"avgvis_km\":9.9,\"avgvis_miles\":6.0,\"avghumidity\":55.0,\"condition\":{\"text\":\"Moderate rain at times\",\"icon\":\"//cdn.apixu.com/weather/64x64/day/299.png\",\"code\":1186},\"uv\":6.1},\"astro\":{\"sunrise\":\"06:18 AM\",\"sunset\":\"09:36 PM\",\"moonrise\":\"02:34 AM\",\"moonset\":\"06:19 PM\"}}]},\"alert\":{}}"


   fun makeXuForecastWeatherResponse(day : Int): XuWeatherForecast {
       val xuWeatherForecast = Gson().fromJson(mock4DayResponse, XuWeatherForecast::class.java)
       val forecast = Forecast(xuWeatherForecast.forecast.forecastday.subList(0, day));
       return  XuWeatherForecast(xuWeatherForecast.location, xuWeatherForecast.current, forecast)
   }




    fun makeWeatherForecast(xuForecastToWeatherForecast: XuForecastToWeatherForecast, xuWeatherForecast: XuWeatherForecast) : WeatherForecast {
        return runBlocking { xuForecastToWeatherForecast.map(xuWeatherForecast)};
    }


}