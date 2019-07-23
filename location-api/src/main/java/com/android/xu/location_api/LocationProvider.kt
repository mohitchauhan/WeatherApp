package com.android.xu.location_api

import com.android.xu.location_api.model.Location

interface LocationProvider {
    suspend fun getLocation(): Location

}