package com.android.xu.weather.location

import android.Manifest
import androidx.annotation.NonNull
import androidx.annotation.RequiresPermission
import com.android.xu.location_api.LocationProvider
import com.android.xu.location_api.model.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Tasks


class LocationProviderImpl  constructor(val fusedLocationProviderClient: FusedLocationProviderClient) :
    LocationProvider {


    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    @NonNull
    override suspend fun getLocation(): Location {
        return getlastLocation();
    }

    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    @NonNull
    private fun getlastLocation() : Location {
        val task = fusedLocationProviderClient.lastLocation
        val result = Tasks.await(task);
        return Location(
            result.latitude,
            result.longitude
        )

    }


}
