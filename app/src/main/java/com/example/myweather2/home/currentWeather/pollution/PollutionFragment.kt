package com.example.myweather2.home.currentWeather.pollution

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.myweather2.MainViewModel
import com.example.myweather2.R
import com.example.myweather2.home.currentWeather.WeatherUtil
import com.example.myweather2.network.AirPollution
import com.example.myweather2.network.AirPollutionData
import com.example.myweather2.network.OneCallAPI

class PollutionFragment : Fragment() {
    private lateinit var airQualityTextView: TextView
    private lateinit var coConcentrationTextView: TextView
    private lateinit var noConcentrationTextView: TextView
    private lateinit var no2ConcentrationTextView: TextView
    private lateinit var o3ConcentrationTextView: TextView
    private lateinit var so2ConcentrationTextView: TextView
    private lateinit var pm2_5ConcentrationTextView: TextView
    private lateinit var pm10ConcentrationTextView: TextView
    private lateinit var nh3ConcentrationTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_pollution, container, false)

        airQualityTextView = root.findViewById(R.id.airQualityTextView)
        coConcentrationTextView = root.findViewById(R.id.coConcentrationTextView)
        noConcentrationTextView = root.findViewById(R.id.noConcentrationTextView)
        no2ConcentrationTextView = root.findViewById(R.id.no2ConcentrationTextView)
        o3ConcentrationTextView = root.findViewById(R.id.o3ConcentrationTextView)
        so2ConcentrationTextView = root.findViewById(R.id.so2ConcentrationTextView)
        pm2_5ConcentrationTextView = root.findViewById(R.id.pm2_5ConcentrationTextView)
        pm10ConcentrationTextView = root.findViewById(R.id.pm10ConcentrationTextView)
        nh3ConcentrationTextView = root.findViewById(R.id.nh3ConcentrationTextView)

        val mainViewModel =
                ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        mainViewModel.apiResult.observe(viewLifecycleOwner, { result ->
            if (result == null) {
                Log.d("MyWeather2", "ForecastFragment: Waiting for API result")
            } else {
                if (result.success) {
                    Log.d("MyWeather2", "ForecastFragment: Got API result!")
                    updateWeatherData(result.pollution!!)
                } else {
                    Log.d("MyWeather2", "ForecastFragment: Failed to get API result!")
                }
            }
        })

        return root
    }

    private fun updateWeatherData(pollution: AirPollution) {
        val airPollutionData = pollution.list.first()

        airQualityTextView.text = WeatherUtil.formatAirQualityIndex(airPollutionData.main.aqi)
        coConcentrationTextView.text = WeatherUtil.formatConcentration(airPollutionData.components.co)
        noConcentrationTextView.text = WeatherUtil.formatConcentration(airPollutionData.components.no)
        no2ConcentrationTextView.text = WeatherUtil.formatConcentration(airPollutionData.components.no2)
        o3ConcentrationTextView.text = WeatherUtil.formatConcentration(airPollutionData.components.o3)
        so2ConcentrationTextView.text = WeatherUtil.formatConcentration(airPollutionData.components.so2)
        pm2_5ConcentrationTextView.text = WeatherUtil.formatConcentration(airPollutionData.components.pm2_5)
        pm10ConcentrationTextView.text = WeatherUtil.formatConcentration(airPollutionData.components.pm10)
        nh3ConcentrationTextView.text = WeatherUtil.formatConcentration(airPollutionData.components.nh3)

    }
}