package com.android.xu.data.repositories.forecast

import com.android.xu.core.annotations.XuService
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherForecastModule {

    @Binds
    @XuService
    abstract fun bindDataSource(source: WeatherForecastDataSource): WeatherForecastDataSource

}