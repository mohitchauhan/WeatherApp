package com.android.app.weather.inject

import android.content.Context
import com.android.xu.location_api.LocationProvider
import com.android.app.weather.location.LocationProviderImpl
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides

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