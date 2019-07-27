package com.android.app.weather.inject

import android.app.Application
import com.android.app.weather.AppInitializer
import com.android.app.weather.WeatherApp
import com.android.app.weather.appinitializers.RxAndroidInitializer
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class AppModuleBinds {

    @Binds
    abstract fun provideApplication(bind: WeatherApp): Application


    @Binds
    @IntoSet
    abstract fun provideRxAndroidInitializer(bind: RxAndroidInitializer): AppInitializer


}