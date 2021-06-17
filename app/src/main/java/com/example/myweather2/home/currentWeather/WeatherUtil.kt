package com.example.myweather2.home.currentWeather

import com.example.myweather2.R

object WeatherUtil {
    fun weatherToIcon(icon: String): Int {
        return when (icon) {
            "01d", "01n" -> R.drawable.weather_sunny
            "02d", "02n" -> R.drawable.weather_partly_cloudy
            "03d", "03n" -> R.drawable.weather_cloudy
            "04d", "04n" -> R.drawable.weather_cloudy
            "09d", "09n" -> R.drawable.weather_partly_rainy
            "10d", "10n" -> R.drawable.weather_pouring
            "11d", "11n" -> R.drawable.weather_lightning
            "13d", "13n" -> R.drawable.weather_snow
            "50d", "50n" -> R.drawable.weather_fog
            else -> R.drawable.alert_circle
        }
    }

    fun formatUnix(dt: Int, format: String): String {
        val sdf = java.text.SimpleDateFormat(format)
        val date = java.util.Date(dt * 1000L)
        return sdf.format(date)
    }

    fun formatTemperature(temperature: Double): String {
        return "${temperature.toInt()}°C"
    }

    fun formatPressure(pressure: Int): String {
        return "$pressure hPa"
    }

    fun formatWindSpeed(windSpeed: Double): String {
        return "${windSpeed.toInt()} m/s"
    }

    fun formatAirQualityIndex(aqi: Int): String {
        return when (aqi) {
            1 -> "Good"
            2 -> "Fair"
            3 -> "Moderate"
            4 -> "Poor"
            5 -> "Very Poor"
            else -> ""
        }
    }

    fun formatConcentration(concentration: Double): String {
        return "$concentration μg/m³"
    }
}