package com.example.weatherman

//Represents a single piece of data - provides no functionality
//Data classes require you to define at least a single property
data class DailyForecast (
        val temp: Float,
        val description: String
)