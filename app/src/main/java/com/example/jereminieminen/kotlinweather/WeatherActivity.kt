package com.example.jereminieminen.kotlinweather

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_weather.*
import org.json.JSONObject

class WeatherActivity : Activity() {
    private val requestUrl = "http://api.openweathermap.org/data/2.5/weather?q=Helsinki&appid=6c433438776b5be4ac86001dc88de74d"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        openForecastButton.setOnClickListener {
            // Start another activity with intent
            val openForecastIntent = Intent(this, WeatherForecastActivity::class.java)

            val location: String = locationTextView.text.toString()

            // Datan passaaminen intentissä openForecastIntent.putExtra("infoToPass", variabletoPass)

            startActivity(openForecastIntent)
        }

        val getWeatherButton = findViewById<Button>(R.id.weatherButton)

        getWeatherButton.setOnClickListener {
            val queue = Volley.newRequestQueue(this);
            val stringRequest = StringRequest(Request.Method.GET, requestUrl,
                    Response.Listener<String> { response ->
                        Toast.makeText(this, response, Toast.LENGTH_LONG).show()
                        parseJsonAndUpdateUI(response);
                    },
                    Response.ErrorListener {
                        Toast.makeText(applicationContext, "Error when fetching weather data", Toast.LENGTH_LONG).show()
                    })
            queue.add(stringRequest)
        }
    }

    fun parseJsonAndUpdateUI(jsonResponse: String) {
        val weatherObject = JSONObject(jsonResponse);
        val temperature = weatherObject.getJSONObject("main").getDouble("temp")
        val windSpeed = weatherObject.getJSONObject("wind").getDouble("speed")
        val name = weatherObject.get("name")

        tmprTextView.text = "" + temperature
        windTextView.text = "" + windSpeed
        locationTextView.text = name.toString()
    }
}