package com.android.xu.weather.inject

import com.android.xu.data.DataModule
import com.android.xu.weather.WeatherApp
import com.android.xu.weather.home.HomeBuilder
import com.android.xu.xuapi.APIxuModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    HomeBuilder::class,
    AppModule::class,
    APIxuModule::class,
    NetworkModule::class,
    DataModule::class,
    LocationModule::class
])
interface AppComponent : AndroidInjector<WeatherApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: WeatherApp): AppComponent
    }
}
