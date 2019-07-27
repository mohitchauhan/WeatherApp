package com.android.app.weather

import androidx.test.InstrumentationRegistry
import com.android.app.weather.di.DaggerTestAppComponent
import com.android.app.weather.di.TestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class WeatherTestApplication : DaggerApplication() {

    lateinit var  appComponent : TestAppComponent;

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
         appComponent =  DaggerTestAppComponent.factory().create(this)
        return appComponent;
    }



    companion object {
        fun appComponent(): TestAppComponent {
            return (InstrumentationRegistry.getTargetContext().applicationContext as WeatherTestApplication).appComponent
        }
    }


}