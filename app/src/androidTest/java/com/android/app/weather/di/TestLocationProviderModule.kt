package com.android.app.weather.di

import com.android.xu.location_api.LocationProvider
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestLocationProviderModule {
    @Provides
    @Singleton
    fun bindLocationProvider(): LocationProvider {
        return mock()
    }
}