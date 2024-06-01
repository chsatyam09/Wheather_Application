package com.placement.wheatherbyeasytuto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.placement.wheatherbyeasytuto.Api.Constant
import com.placement.wheatherbyeasytuto.Api.NetworkResponse
import com.placement.wheatherbyeasytuto.Api.RetrofitInstance
import com.placement.wheatherbyeasytuto.Api.WeatherModel
import kotlinx.coroutines.launch


class WeatherViewModel  : ViewModel() {
    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
     val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult
    fun getData(city: String) {
        _weatherResult.value = NetworkResponse.Loading

        viewModelScope.launch {

            try {
                val response = weatherApi.getWeather(Constant.apiKey, city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)

                    }
                }
                else {
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }
            } // try
            catch (e : Exception){
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
            }

        } //viewModelScope.launch

    }
}




