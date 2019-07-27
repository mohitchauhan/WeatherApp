package com.android.app.weather.di

import com.android.xu.data.mappers.XuForecastToWeatherForecast
import com.android.xu.location_api.LocationProvider
import com.android.app.weather.WeatherTestApplication
import com.android.app.weather.home.HomeBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, TestAppModule::class, TestLocationProviderModule::class, TestDataModule::class,  TestAPIxuModule::class, HomeBuilder::class])
interface TestAppComponent  : AndroidInjector<WeatherTestApplication> {

    fun locationProvider(): LocationProvider

    fun xuWeatherMapper() : XuForecastToWeatherForecast

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: WeatherTestApplication): TestAppComponent
    }



}