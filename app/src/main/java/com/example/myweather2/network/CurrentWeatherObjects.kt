package com.example.myweather2.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeather(
    val coord: Coord,
    val weather: List<WeatherData>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Int,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int,
)

@JsonClass(generateAdapter = true)
data class Coord(
    val lon: Double,
    val lat: Double,
)

@JsonClass(generateAdapter = true)
data class WeatherData(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)

@JsonClass(generateAdapter = true)
data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
)

@JsonClass(generateAdapter = true)
data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double?,
)

@JsonClass(generateAdapter = true)
data class Clouds(
    val all: Int,
)

@JsonClass(generateAdapter = true)
data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Int,
    val sunset: Int,
)
