package com.example.myweather2.home.currentWeather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather2.MainViewModel
import com.example.myweather2.R
import com.example.myweather2.network.OneCallAPI
import com.example.myweather2.network.Weather
import org.w3c.dom.Text

class CurrentWeatherFragment : Fragment() {
    // Current weather
    private lateinit var weatherIcon: ImageView
    private lateinit var temperatureTextView: TextView

    // 48h forecast
    private lateinit var forecast48hRecyclerView: RecyclerView
    private lateinit var forecast48hAdapter: HourlyForecastAdapter

    // Buttons
    private lateinit var alertsButton: Button
    private lateinit var forecast7daysButton: Button
    private lateinit var pollutionButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_current_weather, container, false)

        // Current weather
        weatherIcon = root.findViewById(R.id.weatherIcon)
        temperatureTextView = root.findViewById(R.id.temperatureTextView)

        // 48h forecast
        forecast48hRecyclerView = root.findViewById(R.id.forecast48hRecyclerView)
        forecast48hAdapter = HourlyForecastAdapter()
        forecast48hRecyclerView.adapter = forecast48hAdapter
        forecast48hRecyclerView.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        )

        // Buttons
        alertsButton = root.findViewById(R.id.alertsButton)
        forecast7daysButton = root.findViewById(R.id.forecast7daysButton)
        pollutionButton = root.findViewById(R.id.pollutionButton)

        alertsButton.setOnClickListener {
            findNavController().navigate(R.id.action_currentWeatherFragment_to_alertFragment)
        }
        forecast7daysButton.setOnClickListener {
            findNavController().navigate(R.id.action_currentWeatherFragment_to_forecastFragment)
        }
        pollutionButton.setOnClickListener {
            findNavController().navigate(R.id.action_currentWeatherFragment_to_pollutionFragment)
        }

        val mainViewModel =
                ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        mainViewModel.apiResult.observe(viewLifecycleOwner, { result ->
            if (result == null) {
                Log.d("MyWeather2", "CurrentWeatherFragment: Waiting for API result")
            } else {
                if (result.success) {
                    Log.d("MyWeather2", "CurrentWeatherFragment: Got API result!")
                    updateWeatherData(result.weather!!)
                } else {
                    Log.d("MyWeather2", "CurrentWeatherFragment: Failed to get API result!")
                }
            }
        })

        return root
    }

    private fun updateWeatherData(api: OneCallAPI) {
        val currentWeather = api.current.weather.first()

        weatherIcon.setImageResource(WeatherUtil.weatherToIcon(currentWeather.icon))
        temperatureTextView.text = "${api.current.temp.toInt()}°C"

        forecast48hAdapter.submitList(api.hourly)
    }
}