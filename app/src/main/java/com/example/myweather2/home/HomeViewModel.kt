package com.example.myweather2.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather2.BuildConfig
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val apiKey = BuildConfig.APIKEY

    // TODO: Get that from Room instead?
    private val _citiesList: MutableList<String> = mutableListOf("Gliwice", "Zabrze", "Katowice")

    private val _cities: MutableLiveData<List<String>> = MutableLiveData()
    val cities: LiveData<List<String>>
        get() = _cities

    private val _addCityResult: MutableLiveData<Boolean> = MutableLiveData(true)
    val addCityResult: LiveData<Boolean>
        get() = _addCityResult

    init {
        _cities.value = _citiesList
    }

    fun deleteCity(city: String) {
        _citiesList.remove(city)
        _cities.value = _citiesList
    }

    fun addCity(city: String) {
        viewModelScope.launch {
            tryAddCity(city)
        }
    }

    private suspend fun tryAddCity(city: String) {
        val service = OpenWeatherApi.retrofitService

        try {
            val currentWeather = service.getCurrentWeather(city, apiKey)
            _citiesList.add(city)
            _cities.value = _citiesList
            _addCityResult.value = true
        } catch (exception: Exception) {
            // TODO: Handle invalid city and no connection exceptions differently
            Log.d("MyWeather2", "Failed to add city ${city}")
            Log.d("MyWeather2", "Message: ${exception.message}")
            Log.d("MyWeather2", "Cause: ${exception.cause}")
            _addCityResult.value = false
        }
    }
}