package com.example.myweather2.home.currentWeather.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather2.R
import com.example.myweather2.home.currentWeather.WeatherUtil
import com.example.myweather2.network.Daily

class ForecastAdapter : ListAdapter<Daily, ForecastAdapter.ViewHolder>(
        DailyDiffCallback()
) {
    class ViewHolder(
            view: View
    ) : RecyclerView.ViewHolder(view) {
        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        val weatherIcon: ImageView = view.findViewById(R.id.weatherIcon)
        val temperatureTextView: TextView = view.findViewById(R.id.temperatureTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.daily_forecast_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val daily = getItem(position)

        holder.dateTextView.text = WeatherUtil.formatUnix(daily.dt, "yyyy-MM-dd")
        holder.weatherIcon.setImageResource(WeatherUtil.weatherToIcon(daily.weather.first().icon))
        holder.temperatureTextView.text = WeatherUtil.formatTemperature(daily.temp.day)
    }
}

class DailyDiffCallback : DiffUtil.ItemCallback<Daily>() {
    override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean {
        return oldItem == newItem
    }
}