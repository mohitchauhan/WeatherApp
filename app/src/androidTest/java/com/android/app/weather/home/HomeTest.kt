package com.android.app.weather.home

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.android.xu.location_api.model.Location
import com.android.xu.weather.R
import com.android.app.weather.WeatherTestApplication
import com.android.app.weather.data.TestData
import com.android.app.weather.mockserver.MockServerDispatcher
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random


@RunWith(AndroidJUnit4::class)
class HomeTest {


    @Rule
    @JvmField
    val activity = ActivityTestRule<HomeActivity>(HomeActivity::class.java, false, false)

    @Rule
    @JvmField
    var permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)


    private lateinit var webServer: MockWebServer

    @Before
    @Throws(Exception::class)
    fun setup() {
        webServer = MockWebServer()
        webServer.start(8080)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        webServer.shutdown()
    }

    @Test
    fun activityLaunches() {
        stubLocationProvider()
        activity.launchActivity(null)
    }


    @Test
    fun displayWeatherContent(){
        stubLocationProvider()

        val weather  = TestData.makeWeatherForecast(WeatherTestApplication.appComponent().xuWeatherMapper(), TestData.makeXuForecastWeatherResponse(Random.nextInt(1, 4)))

        webServer.dispatcher = MockServerDispatcher.RequestDispatcher(TestData.mock4DayResponse)

        activity.launchActivity(null)

        Espresso.onView(ViewMatchers.withId(R.id.currentTemperature))
            .check(ViewAssertions.matches(ViewMatchers.withText(weather.currentTemp_c.toString())))

        Espresso.onView(ViewMatchers.withId(R.id.cityName))
            .check(ViewAssertions.matches(ViewMatchers.withText(weather.cityName)))


        weather.days.forEachIndexed { index, forcast ->
            Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(forcast.date))))
            Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(forcast.day.maxtemp_c.toString()))))
        }
    }


    @Test
    fun onErrorShowsRetry() {
        stubLocationProvider()
        webServer.dispatcher = MockServerDispatcher.ErrorDispatcher()
        activity.launchActivity(null)
        Espresso.onView(ViewMatchers.withId(R.id.retry_button))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }




    private fun stubLocationProvider() {
        runBlocking {
            whenever(WeatherTestApplication.appComponent().locationProvider().getLocation()).thenReturn(
                Location(
                    2.0,
                    2.0
                )
            )
        }


    }



}