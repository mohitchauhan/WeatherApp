package com.android.xu.weather.inject

import android.content.Context
import com.android.xu.location_api.LocationProvider
import com.android.xu.weather.location.LocationProviderImpl
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideLocationClient(context: Context): FusedLocationProviderClient {
            return FusedLocationProviderClient(context)
        }
    }

    @Provides
    fun bindLocationProvider(client: FusedLocationProviderClient): LocationProvider{
        return LocationProviderImpl(client)
    }
}