package com.android.app.weather.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.xu.core.data.entities.ErrorResult
import com.android.xu.core.data.entities.Result
import com.android.xu.core.state.Resource
import com.android.xu.core.state.ResourceState
import com.android.xu.data.entities.WeatherForecast
import com.android.xu.interactors.WeatherForecastDetails
import com.android.xu.interactors.launchInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val weatherForecastInteractor : WeatherForecastDetails) : ViewModel() {

    val liveData: MutableLiveData<Resource<WeatherForecast>> = MutableLiveData();


    val disposables = CompositeDisposable()

    companion object{
        const val DAYS = 4;
    }

    init {
        disposables.add(weatherForecastInteractor.observe().subscribeWith(object: DisposableObserver<Result<WeatherForecast>>() {
            override fun onComplete() {
            }
            override fun onNext(t: Result<WeatherForecast>) {
                if (t is ErrorResult){
                    liveData.postValue(Resource(ResourceState.ERROR, null, t.exception?.message))
                }else{
                    liveData.postValue(Resource(ResourceState.SUCCESS, t.get(), "success"))
                }
            }

            override fun onError(e: Throwable) {
                liveData.postValue(Resource(ResourceState.ERROR, null, e.message))
            }
        }))
    }

    fun fetchWeatherForecast(days : Int = DAYS){
        Log.d("fetchWeatherForecast", " " + days + weatherForecastInteractor.dispatcher + weatherForecastInteractor)
        liveData.postValue(Resource(ResourceState.LOADING, null, "Loading data"))
        viewModelScope.launchInteractor(weatherForecastInteractor, getParams(days))
    }

    fun getParams(days: Int) = WeatherForecastDetails.Params(days)


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }



}