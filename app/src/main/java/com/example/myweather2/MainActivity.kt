package com.example.myweather2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)
    }
}