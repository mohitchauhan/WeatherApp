package com.android.app.weather

import com.android.app.weather.appinitializers.AppInitializers
import com.android.app.weather.inject.DaggerAppComponent
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