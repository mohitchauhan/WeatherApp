package com.android.xu.interactors

import com.android.xu.core.data.entities.ErrorResult
import com.android.xu.core.data.entities.Result
import com.android.xu.core.utils.AppCoroutineDispatchers
import com.android.xu.data.entities.WeatherForecast
import com.android.xu.data.repositories.forecast.GetForecastRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class WeatherForecastDetails @Inject constructor(private val weatherForecastRepository : GetForecastRepository,  dispatchers: AppCoroutineDispatchers) : SuspendingWorkInteractor<WeatherForecastDetails.Params,  Result<WeatherForecast>>(){
    override val dispatcher: CoroutineDispatcher = dispatchers.io

    override suspend fun doWork(params: Params): Result<WeatherForecast> {
        try {
            return weatherForecastRepository.getForecast(params.days);
        } catch (e: Exception) {
            e.printStackTrace()
            return ErrorResult(e, "Error occurred")
        }
    }
    
    data class Params(val days : Int)

}