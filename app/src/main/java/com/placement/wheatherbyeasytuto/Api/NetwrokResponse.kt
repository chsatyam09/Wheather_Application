package com.placement.wheatherbyeasytuto.Api




//T refers to WeatherModel
sealed class NetworkResponse<out T> { // Sealed class  ka Generics create karne wala hai
    data class Success<out T>(val data : T) : NetworkResponse<T>()
    data class Error(val message : String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
}