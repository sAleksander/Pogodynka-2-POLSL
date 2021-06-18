package com.example.myweather2.home.currentWeather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather2.R
import com.example.myweather2.network.Hourly

class HourlyForecastAdapter : ListAdapter<Hourly, HourlyForecastAdapter.ViewHolder>(
        HourlyForecastAdapterDiffCallback()
) {
    class ViewHolder(
            view: View
    ) : RecyclerView.ViewHolder(view) {
        val hourTextView: TextView = view.findViewById(R.id.hourTextView)
        val weatherIcon: ImageView = view.findViewById(R.id.weatherIcon)
        val temperatureTextView: TextView = view.findViewById(R.id.temperatureTextView)
        val popTextView: TextView = view.findViewById(R.id.popTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.hourly_forecast_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hourlyForecast = getItem(position)

        val sdf = java.text.SimpleDateFormat("HH:mm")
        val date = java.util.Date(hourlyForecast.dt * 1000L)
        val hour = sdf.format(date)

        holder.hourTextView.text = WeatherUtil.formatUnix(hourlyForecast.dt, "HH:mm")
        holder.weatherIcon.setImageResource(WeatherUtil.weatherToIcon(hourlyForecast.weather.first().icon))
        holder.temperatureTextView.text = WeatherUtil.formatTemperature(hourlyForecast.temp)
        holder.popTextView.text = "${(hourlyForecast.pop * 100).toInt()}%"
    }
}

class HourlyForecastAdapterDiffCallback : DiffUtil.ItemCallback<Hourly>() {
    override fun areItemsTheSame(oldItem: Hourly, newItem: Hourly): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Hourly, newItem: Hourly): Boolean {
        return oldItem == newItem
    }
}