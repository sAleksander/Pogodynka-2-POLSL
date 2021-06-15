package com.example.myweather2.home.currentWeather.alert

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
import com.example.myweather2.home.currentWeather.WeatherUtil
import com.example.myweather2.network.OneCallAPI


class AlertFragment : Fragment() {
    private lateinit var alertsRecyclerView: RecyclerView
    private lateinit var alertsAdapter: AlertsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_alert, container, false)

        alertsRecyclerView = root.findViewById(R.id.alertsRecyclerView)
        alertsAdapter = AlertsAdapter()
        alertsRecyclerView.adapter = alertsAdapter
        alertsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val mainViewModel =
                ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        mainViewModel.apiResult.observe(viewLifecycleOwner, { result ->
            if (result == null) {
                Log.d("MyWeather2", "AlertFragment: Waiting for API result")
            } else {
                if (result.success) {
                    Log.d("MyWeather2", "AlertFragment: Got API result!")
                    updateWeatherData(result.weather!!)
                } else {
                    Log.d("MyWeather2", "AlertFragment: Failed to get API result!")
                }
            }
        })

        return root
    }

    private fun updateWeatherData(api: OneCallAPI) {
        Log.d("MyWeather2", "AlertFragment: Found ${api.alerts?.size} alerts.")
        alertsAdapter.submitList(api.alerts)
    }
}