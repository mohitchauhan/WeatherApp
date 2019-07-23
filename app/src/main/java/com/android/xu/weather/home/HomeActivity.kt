package com.android.xu.weather.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
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
import kotlinx.android.synthetic.main.e_content_view.view.*


class HomeActivity : BaseActivity() {

     private lateinit var viewModel: HomeViewModel
     private lateinit var forecastAdapter : ForecastAdapter

    private val LOCATION_REQUEST_CODE = 12
    private lateinit var contentViewBinding : ActivityMainBinding;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentViewBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        forecastAdapter = ForecastAdapter(arrayListOf<Forecastday>())
        contentViewBinding.includeContent.recyclerView.apply {
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
                handleResult(it)
            }
        });

        requestPermission()
    }

    private fun handleResult(it: Resource<WeatherForecast>) {
        contentViewBinding.loadingView.visibility = View.GONE
        contentViewBinding.errorView.visibility = View.GONE
        contentViewBinding.includeContent.visibility = View.GONE

        when (it.resourceState) {
            ResourceState.ERROR -> {
                contentViewBinding.errorView.visibility = View.VISIBLE
            }
            ResourceState.SUCCESS -> {
                it.data?.days?.let { it1 -> forecastAdapter.update(it1) };
                contentViewBinding.includeContent.cityName.text = it.data?.cityName
                contentViewBinding.includeContent.currentTemperature.text =
                    it.data?.currentTemp_c.toString()
                contentViewBinding.includeContent.visibility = View.VISIBLE
            }
            else -> {
                contentViewBinding.loadingView.visibility = View.VISIBLE
                // do nothing
            }
        }
    }


    private fun requestPermission() {
        if (hasLocationPermission(this)) {
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(this, permissions, LOCATION_REQUEST_CODE)
        } else {
            onPermissionSuccess()
        }
    }

    private fun hasLocationPermission(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        @NonNull permissions: Array<String>, @NonNull grantResults: IntArray
    ) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onPermissionSuccess()
            } else {
                showPermissionError()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun showPermissionError() {
        Toast.makeText(this.baseContext, getString(R.string.error_location_permission_not_granted), Toast.LENGTH_LONG).show()
        finish()
    }

    private fun onPermissionSuccess() {
        viewModel.fetchWeatherForecast()
    }


}
