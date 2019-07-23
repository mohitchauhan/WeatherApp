package com.android.xu.weather

import android.app.Application
import com.android.xu.weather.appinitializers.AppInitializers
import com.android.xu.weather.inject.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class WeatherApp : DaggerApplication() {

    @Inject
    lateinit var initializers: AppInitializers


    override fun onCreate() {
        super.onCreate()
        initializers.init(this)
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}