package com.android.app.weather.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.app.weather.CoroutinesTestRule
import com.android.app.weather.home.HomeViewModel
import com.android.xu.core.data.entities.ErrorResult
import com.android.xu.core.data.entities.Result
import com.android.xu.core.data.entities.Success
import com.android.xu.core.state.ResourceState
import com.android.xu.data.entities.WeatherForecast
import com.android.xu.data.mappers.XuForecastToWeatherForecast
import com.android.xu.interactors.WeatherForecastDetails
import com.android.app.weather.data.UnitTestData
import com.nhaarman.mockitokotlin2.*
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import java.lang.IllegalStateException


@RunWith(JUnit4::class)
class HomeViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var  viewModel : HomeViewModel;

    val subject : BehaviorSubject<Result<WeatherForecast>> =  BehaviorSubject.create()


    @Captor
    val captor = argumentCaptor<WeatherForecastDetails.Params>()

    @ExperimentalCoroutinesApi
    val interactor = mock<WeatherForecastDetails>();


    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        // provide the scope explicitly, in this example using a constructor parameter
        whenever(interactor.observe()).doReturn(subject)
        whenever(interactor.dispatcher).doReturn(coroutinesTestRule.testDispatcher)
        viewModel = HomeViewModel(interactor);
    }




    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherReturnsLoading()  {
        viewModel.fetchWeatherForecast(3)
        assertEquals(
            viewModel.liveData.value?.resourceState,
            ResourceState.LOADING
        )
    }


    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherReturnsSuccess()  = coroutinesTestRule.testDispatcher.runBlockingTest {
        val apiWeather = UnitTestData.makeXuForecastWeatherResponse(3)
        val weather = UnitTestData.makeWeatherForecast(XuForecastToWeatherForecast(),  apiWeather)
        viewModel.fetchWeatherForecast(3)
        verify(interactor).invoke(eq(WeatherForecastDetails.Params(3)))
        subject.onNext(Success(weather))
        assertEquals(viewModel.liveData.value?.resourceState, ResourceState.SUCCESS)
    }



    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherReturnsData()  = coroutinesTestRule.testDispatcher.runBlockingTest {
        val apiWeather = UnitTestData.makeXuForecastWeatherResponse(3)
        val weather = UnitTestData.makeWeatherForecast(XuForecastToWeatherForecast(),  apiWeather)
        viewModel.fetchWeatherForecast(3)
        verify(interactor).invoke(eq(WeatherForecastDetails.Params(3)))
        subject.onNext(Success(weather))
        assertEquals(weather, viewModel.liveData.value?.data)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherReturnsError()  = coroutinesTestRule.testDispatcher.runBlockingTest{
        val error = ErrorResult<WeatherForecast>(IllegalStateException("something went wrong"), "something went wrong");
        viewModel.fetchWeatherForecast(3)
        verify(interactor).invoke(eq(WeatherForecastDetails.Params(3)))
        subject.onNext(error)
        assertEquals(viewModel.liveData.value?.message, "something went wrong")
    }

}