package com.example.weatherman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipCodeEditText: EditText = findViewById(R.id.zipCodeEditText) // we now have a reference to the edit text displayed on the screen. We can now manipulate it as we desire
        val enterButton: Button = findViewById(R.id.enterButton)

        enterButton.setOnClickListener {
            val zipCode: String = zipCodeEditText.text.toString()

            if ( zipCode.length != 5){
                Toast.makeText(this, R.string.zipcode_entry_error, Toast.LENGTH_SHORT).show()
            } else {
                forecastRepository.LoadForecast(zipCode)
            }
        }

        val forecastList: RecyclerView = findViewById(R.id.forecastList)
        forecastList.layoutManager = LinearLayoutManager(this)
        val dailyForecastAdapter = DailyForecastAdapter { forecastItem->

            val msg = getString(R.string.forecast_clicked_format, forecastItem.temp, forecastItem
            .description)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        forecastList.adapter = dailyForecastAdapter

        // ADDING AN OBSERVER TO OUR REPOSITORY. THE OBSERVER LET'S US KNOW OF ANY CHANGES TO THE
        // DATA
        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItem ->
            //update our list adapter
            dailyForecastAdapter.submitList(forecastItem)
        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)
    }
}