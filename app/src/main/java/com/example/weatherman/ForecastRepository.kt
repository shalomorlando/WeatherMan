    package com.example.weatherman

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

//A repository does two things for us - 1) It loads the data and 2) Makes the data available for us in the activity
class ForecastRepository {
    //private read only property that is assigned a mutableLiveData which holds a list of DailyForecast items - local to this
    //repository class - useful for performing updates
    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()

    /*  public live data to make this data available to our activity This means that any external
        component that references it can receive updates but these components may not publish
        their own changes to the LiveData -- We want the repository to be the only place that can
         modify this data. */
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    /*Mechanism to load data for a particular location given its zip code*/
    fun LoadForecast(zipCode: String){
        val randomValues = List(7) { Random.nextFloat().rem(100) * 100}
        //creating a list of forecast items using the above random values which rep our temperatures
        val forecastItems = randomValues.map { temp ->
            DailyForecast(temp, getTempDescription(temp))
        }
        _weeklyForecast.setValue(forecastItems)
    }

    private fun getTempDescription(temp: Float) : String  {
        return when(temp){
            in Float.MIN_VALUE.rangeTo(0f) -> "Anything below zero does not make sense"
            in 0f.rangeTo(32f) -> "Way too cold"
            in 32f.rangeTo(55f) -> "Colder that I would prefer"
            in 55f.rangeTo(65f) -> "Getting better"
            in 65f.rangeTo(80f) -> "That's the sweet spot!"
            in 80f.rangeTo(90f) -> "Getting a little warm"
            in 90f.rangeTo(100f) -> "Where's the A/C?"
            in 100f.rangeTo(Float.MAX_VALUE) -> "What is this, Arizona?"
            else -> "Does not compute"
        }

    }

}