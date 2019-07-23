package com.android.xu.weather.inject

import android.app.Application
import com.android.xu.weather.AppInitializer
import com.android.xu.weather.WeatherApp
import com.android.xu.weather.appinitializers.RxAndroidInitializer
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