package com.example.myweather2.home.currentWeather.alert

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather2.R
import com.example.myweather2.home.currentWeather.WeatherUtil
import com.example.myweather2.network.Alert

class AlertsAdapter : ListAdapter<Alert, AlertsAdapter.ViewHolder>(
        AlertDiffCallback()
) {
    class ViewHolder(
            view: View
    ) : RecyclerView.ViewHolder(view) {
        val senderTextView: TextView = view.findViewById(R.id.senderTextView)
        val eventTextView: TextView = view.findViewById(R.id.eventTextView)
        val startTextView: TextView = view.findViewById(R.id.startTextView)
        val endTextView: TextView = view.findViewById(R.id.endTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.hourly_forecast_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val alert = getItem(position)

        holder.senderTextView.text = alert.senderName
        holder.eventTextView.text = alert.event
        holder.startTextView.text = WeatherUtil.formatUnix(alert.start, "yyyy-MM-dd HH:mm")
        holder.endTextView.text = WeatherUtil.formatUnix(alert.end, "yyyy-MM-dd HH:mm")
        holder.descriptionTextView.text = alert.description
    }
}

class AlertDiffCallback : DiffUtil.ItemCallback<Alert>() {
    override fun areItemsTheSame(oldItem: Alert, newItem: Alert): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Alert, newItem: Alert): Boolean {
        return oldItem == newItem
    }
}