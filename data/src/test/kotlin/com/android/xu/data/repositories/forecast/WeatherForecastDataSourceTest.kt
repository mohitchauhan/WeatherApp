package com.android.xu.data.repositories.forecast

import com.android.xu.CoroutinesTestRule
import com.android.xu.core.data.entities.Success
import com.android.xu.data.WeatherTestData
import com.android.xu.data.entities.WeatherForecast
import com.android.xu.data.mappers.XuForecastToWeatherForecast
import com.android.xu.xuapi.APIXu
import com.android.xu.xuapi.entities.XuWeatherForecast
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import retrofit2.Response

@RunWith(JUnit4::class)
class WeatherForecastDataSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val apiXu: APIXu = mock()
    private  val  weatherForecastMapper: XuForecastToWeatherForecast = mock()
    private val dataSource  = WeatherForecastDataSource(apiXu, weatherForecastMapper)

    @ExperimentalCoroutinesApi
    @Test
    fun getWeatherForecast() = coroutinesTestRule.testDispatcher.runBlockingTest{
        val apiResponse = WeatherTestData.makeXuApiResponse()
        val makeWeatherForecast = WeatherTestData.makeWeatherForecast()
        stubMapper(apiResponse, makeWeatherForecast)
        val response  =  Response.success(apiResponse)
        whenever(apiXu.forecast(anyString(), anyInt())).thenReturn(response)
        val result = Success(makeWeatherForecast, false);
        val weatherForecast = dataSource.getWeatherForecast("4.0, 3.4", 4)
        assertEquals(result, weatherForecast)
    }


    @ExperimentalCoroutinesApi
    private fun stubMapper(weatherForcast: XuWeatherForecast, weather: WeatherForecast) =  coroutinesTestRule.testDispatcher.runBlockingTest{
        whenever(weatherForecastMapper.map(weatherForcast)).thenReturn(weather)
    }
}