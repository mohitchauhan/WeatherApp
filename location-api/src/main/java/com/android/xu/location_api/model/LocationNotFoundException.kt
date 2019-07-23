package com.dev.lokeshkalal.weatherapp.repository.location.model

import java.lang.RuntimeException

class LocationNotFoundException : RuntimeException {

    constructor(throwable: Throwable) : super(throwable)

    constructor(message: String) : super(message)

}