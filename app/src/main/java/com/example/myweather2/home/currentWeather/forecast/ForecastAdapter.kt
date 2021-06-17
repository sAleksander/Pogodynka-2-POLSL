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
        val dayTemperatureTextView: TextView = view.findViewById(R.id.dayTemperatureTextView)
        val nightTemperatureTextView: TextView = view.findViewById(R.id.nightTemperatureTextView)
        val windTextView: TextView = view.findViewById(R.id.windTextView)
        val pressureTextView: TextView = view.findViewById(R.id.pressureTextView)
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
        holder.dayTemperatureTextView.text = WeatherUtil.formatTemperature(daily.temp.day)
        holder.nightTemperatureTextView.text = WeatherUtil.formatTemperature(daily.temp.night)
        holder.windTextView.text = WeatherUtil.formatWindSpeed(daily.windSpeed)
        holder.pressureTextView.text = WeatherUtil.formatPressure(daily.pressure)
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