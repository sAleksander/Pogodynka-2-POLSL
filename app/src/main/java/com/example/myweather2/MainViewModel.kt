package com.example.myweather2

import OpenWeatherApi
import OpenWeatherApiService
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather2.network.OneCallAPI
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val apiKey = BuildConfig.APIKEY

    private var _currentCity: String? = null
    val currentCity get() = _currentCity

    private var _weather: MutableLiveData<OneCallAPI> = MutableLiveData()
    val weather: LiveData<OneCallAPI>
        get() = _weather

    fun chooseCity(city: String) {
        _currentCity = city
        _weather.value = null

        viewModelScope.launch { downloadWeatherData() }
    }

    private suspend fun downloadWeatherData() {
        val service = OpenWeatherApi.retrofitService

        try {
            Log.d("MyWeather2", "Getting current weather for city $_currentCity")
            val currentWeather = service.getCurrentWeather(_currentCity!!, apiKey)

            val latitude = currentWeather.coord.lat.toString()
            val longitude = currentWeather.coord.lon.toString()

            Log.d("MyWeather2", "One call API for $latitude, $longitude")
            _weather.value = service.oneCallAPI(latitude, longitude, apiKey)
        } catch (exception: Exception) {
            Log.d("MyWeather2", "Failed to download data from API")
            Log.d("MyWeather2", "Message: ${exception.message}")
            Log.d("MyWeather2", "Cause: ${exception.cause}")
        }
    }
}