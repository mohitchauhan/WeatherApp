package com.android.xu.weather.home

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.xu.core.state.Resource
import com.android.xu.core.state.ResourceState
import com.android.xu.data.entities.Forecastday
import com.android.xu.data.entities.WeatherForecast
import com.android.xu.weather.BaseActivity
import com.android.xu.weather.R
import com.android.xu.weather.databinding.ActivityMainBinding


class HomeActivity : BaseActivity() {

     private lateinit var viewModel: HomeViewModel
     private lateinit var forecastAdapter : ForecastAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contentViewBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        forecastAdapter = ForecastAdapter(arrayListOf<Forecastday>())
        contentViewBinding.recyclerView.apply {
            val itemDecor = DividerItemDecoration(this@HomeActivity, DividerItemDecoration.HORIZONTAL)
            itemDecor.setDrawable(ContextCompat.getDrawable(this@HomeActivity, R.drawable.list_divider)!!)
            addItemDecoration(itemDecor)

            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = forecastAdapter
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(HomeViewModel::class.java)
        viewModel.liveData.observe(this, Observer<Resource<WeatherForecast>> {
            it?.let {
                when(it.resourceState){
                    ResourceState.ERROR -> {

                    }
                    ResourceState.SUCCESS -> {
                        it.data?.days?.let { it1 -> forecastAdapter.update(it1) };
                        contentViewBinding.cityName.text = it.data?.cityName
                        contentViewBinding.currentTemperature.text = it.data?.currentTemp_c.toString()

                    }
                    else -> {
                        // do nothing
                    }
                }

                contentViewBinding.state = it.resourceState

            }
        });

        viewModel.fetchWeatherForecast()
    }
}
