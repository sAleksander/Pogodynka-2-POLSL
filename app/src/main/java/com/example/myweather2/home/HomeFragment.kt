package com.example.myweather2.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather2.MainViewModel
import com.example.myweather2.R


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val mainViewModel =
                ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val homeViewModel =
                ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        val textViewCityName: EditText = root.findViewById(R.id.editTextCity)
        root.findViewById<ImageButton>(R.id.buttonAddCity).setOnClickListener {
            homeViewModel.addCity(textViewCityName.editableText.toString())
            textViewCityName.setText("")
        }

        val adapter = HomeAdapter(
                onClick = { city ->
                    mainViewModel.chooseCity(city)
                    findNavController().navigate(R.id.action_homeFragment_to_currentWeatherFragment)
                },
                onDelete = { city ->
                    homeViewModel.deleteCity(city)
                }
        )

        homeViewModel.cities.observe(viewLifecycleOwner, {
            adapter.submitList(it.toMutableList())
        })

        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewCities)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        homeViewModel.addCityResult.observe(viewLifecycleOwner, { result ->
            if (!result) {
                Log.d("MyWeather2", "Fragment: oops, failed to add city")
                Toast.makeText(context, "City doesn't exist", Toast.LENGTH_LONG).show()
            }
        })

        return root
    }
}