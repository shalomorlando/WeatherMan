package com.example.weatherman.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.weatherman.R

class ForecastDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_details)

        setTitle(R.string.forecast_details)

        val detailsTempText = findViewById<TextView>(R.id.detailsTempText)
        val detailsDescriptionText = findViewById<TextView>(R.id.detailsDescriptionText)

        detailsTempText.text = "81.2" + "\u2103"
        detailsDescriptionText.text = "Mostly sunny"
    }
}