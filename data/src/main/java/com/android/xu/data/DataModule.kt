package com.android.xu.data

import com.android.xu.data.repositories.forecast.WeatherForecastModule
import dagger.Module

@Module(includes = [WeatherForecastModule::class])
abstract class DataModule
