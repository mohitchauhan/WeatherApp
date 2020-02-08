package com.android.app.weather

import com.android.app.weather.appinitializers.AppInitializers
import com.android.app.weather.inject.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kotlinx.coroutines.IO_PARALLELISM_PROPERTY_NAME
import javax.inject.Inject

class WeatherApp : DaggerApplication() {

    @Inject
    lateinit var initializers: AppInitializers


    override fun onCreate() {
        super.onCreate()
        initializers.init(this)
//        System.setProperty(IO_PARALLELISM_PROPERTY_NAME, "2")
//        System.setProperty("kotlinx.coroutines.scheduler.core.pool.size", "2")
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}