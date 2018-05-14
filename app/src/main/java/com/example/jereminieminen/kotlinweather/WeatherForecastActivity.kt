package com.example.jereminieminen.kotlinweather

import android.app.Activity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_weather_forecast.*

class WeatherForecastActivity : Activity() {
    private val requestUrl = "api.openweathermap.org/data/2.5/weather?q=Helsinki&appid=6c433438776b5be4ac86001dc88de74d"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast)

        val name = getIntent().getStringExtra("CITY_ NAME")
        val latitude = intent.getDoubleExtra("LATITUDE", 25.0)
        val longitude = intent.getDoubleExtra("LONGITUDE", 50.0)

        // locationTextView.text = name + "Lat: " + latitude + "Lng: " + longitude
    }
}
