package com.android.xu.location_api.model

class Location(val lat: Double, val lon: Double) {
    fun toQueryParams(): String{
        return "$lat,$lon"
    }
}