package com.android.xu.weather.inject

import android.content.Context
import com.android.xu.core.utils.AppCoroutineDispatchers
import com.android.xu.core.utils.AppRxSchedulers
import com.android.xu.weather.BuildConfig
import com.android.xu.weather.WeatherApp
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.rx2.asCoroutineDispatcher
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [AppModuleBinds::class])
class AppModule {

    @Provides
    fun provideContext(application: WeatherApp): Context = application.applicationContext



    @Singleton
    @Provides
    fun provideRxSchedulers(): AppRxSchedulers = AppRxSchedulers(
        io = Schedulers.io(),
        computation = Schedulers.computation(),
        main = AndroidSchedulers.mainThread()
    )

    @Singleton
    @Provides
    fun provideCoroutineDispatchers(schedulers: AppRxSchedulers) = AppCoroutineDispatchers(
        io = schedulers.io.asCoroutineDispatcher(),
        computation = schedulers.computation.asCoroutineDispatcher(),
        main = Dispatchers.Main
    )



    @Provides
    @Named("xu-api-key")
    fun provideXuWeatherApiKey(): String = BuildConfig.XU_API_KEY


    @Provides
    @Singleton
    @Named("cache")
    fun provideCacheDir(application: WeatherApp): File = application.cacheDir

}