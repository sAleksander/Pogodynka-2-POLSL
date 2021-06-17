package com.example.myweather2.home.currentWeather.forecast

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather2.MainViewModel
import com.example.myweather2.R
import com.example.myweather2.home.currentWeather.alert.AlertsAdapter
import com.example.myweather2.network.OneCallAPI

class ForecastFragment : Fragment() {
    private lateinit var forecastRecyclerView: RecyclerView
    private lateinit var forecastAdapter: ForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_forecast, container, false)

        forecastRecyclerView = root.findViewById(R.id.forecastRecyclerView)
        forecastAdapter = ForecastAdapter()
        forecastRecyclerView.adapter = forecastAdapter
        forecastRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val mainViewModel =
                ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        mainViewModel.apiResult.observe(viewLifecycleOwner, { result ->
            if (result == null) {
                Log.d("MyWeather2", "ForecastFragment: Waiting for API result")
            } else {
                if (result.success) {
                    Log.d("MyWeather2", "ForecastFragment: Got API result!")
                    updateWeatherData(result.weather!!)
                } else {
                    Log.d("MyWeather2", "ForecastFragment: Failed to get API result!")
                }
            }
        })

        return root
    }

    private fun updateWeatherData(api: OneCallAPI) {
        forecastAdapter.submitList(api.daily)
    }
}