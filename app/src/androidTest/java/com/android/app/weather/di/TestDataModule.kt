package com.android.app.weather.di

import com.android.xu.core.annotations.XuService
import com.android.xu.data.mappers.XuForecastToWeatherForecast
import com.android.xu.data.repositories.forecast.WeatherForecastDataSource
import com.android.xu.xuapi.APIXu
import dagger.Module
import dagger.Provides

@Module
class TestDataModule {

    @Provides
    fun providedXuWeatherMapper(): XuForecastToWeatherForecast {
        return XuForecastToWeatherForecast();
    }


    @Provides
    @XuService
    fun bindDataSource(apiXu : APIXu, mapper : XuForecastToWeatherForecast): WeatherForecastDataSource{
        return WeatherForecastDataSource(apiXu, mapper);
    }

}