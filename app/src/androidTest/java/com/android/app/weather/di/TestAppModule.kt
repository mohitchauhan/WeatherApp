package com.android.app.weather.di

import android.content.Context
import com.android.xu.core.utils.AppCoroutineDispatchers
import com.android.xu.core.utils.AppRxSchedulers
import com.android.app.weather.WeatherApp
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import javax.inject.Singleton

@Module
class TestAppModule {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()


    @Provides
    fun provideContext(application: WeatherApp): Context = application.applicationContext



    @Singleton
    @Provides
    fun provideRxSchedulers(): AppRxSchedulers = AppRxSchedulers(
        io = Schedulers.trampoline(),
        computation = Schedulers.trampoline(),
        main = AndroidSchedulers.mainThread()
    )

    @Singleton
    @Provides
    @ExperimentalCoroutinesApi
    fun provideCoroutineDispatchers(schedulers: AppRxSchedulers) = AppCoroutineDispatchers(
        io = testDispatcher,
        computation = testDispatcher,
        main = testDispatcher
    )


}