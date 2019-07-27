package com.android.app.weather.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.xu.data.entities.Forecastday
import com.android.xu.weather.databinding.ItemDayWeatherBinding

class ForecastAdapter(val dayList : ArrayList<Forecastday>) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemDayWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false));
    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.forecastDay = dayList[position]
    }

    fun update(days: java.util.ArrayList<Forecastday>) {
        dayList.clear()
        dayList.addAll(days)
        notifyDataSetChanged()
    }


    inner class ViewHolder(val binding: ItemDayWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}