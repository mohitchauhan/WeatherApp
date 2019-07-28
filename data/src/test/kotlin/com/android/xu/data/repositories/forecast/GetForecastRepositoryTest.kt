package com.android.xu.data.repositories.forecast

import com.android.xu.CoroutinesTestRule
import com.android.xu.core.data.entities.ErrorResult
import com.android.xu.core.data.entities.Success
import com.android.xu.data.WeatherTestData
import com.android.xu.data.entities.WeatherForecast
import com.android.xu.location_api.LocationProvider
import com.android.xu.location_api.model.Location
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import java.net.SocketTimeoutException
@RunWith(JUnit4::class)
class GetForecastRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    val dataSource : WeatherForecastDataSource = mock()
    val locationProvider : LocationProvider = mock()
    val forecastRepository  = GetForecastRepository(dataSource, locationProvider)

    @ExperimentalCoroutinesApi
    @org.junit.Test
    fun getForecastReturnData()  = coroutinesTestRule.testDispatcher.runBlockingTest {
        val apiResponse = WeatherTestData.makeWeatherForecast()
        val weather = Success(apiResponse)
        whenever(dataSource.getWeatherForecast(anyString(), anyInt())).thenReturn(weather)
        whenever(locationProvider.getLocation()).thenReturn(Location(1.0, 1.9))
        val result = forecastRepository.getForecast(4);
        Assert.assertEquals(weather, result)

    }


    @ExperimentalCoroutinesApi
    @org.junit.Test
    fun getForecastReturnError()  = coroutinesTestRule.testDispatcher.runBlockingTest {
        val weather = ErrorResult<WeatherForecast>(SocketTimeoutException("socket timeout"), "socket timeout")
        whenever(dataSource.getWeatherForecast(anyString(), anyInt())).thenReturn(weather)
        whenever(locationProvider.getLocation()).thenReturn(Location(1.0, 1.9))
        val result = forecastRepository.getForecast(4);
        Assert.assertEquals(weather, result)

    }
}
