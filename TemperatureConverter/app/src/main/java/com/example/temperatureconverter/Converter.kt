package com.example.temperatureconverter

class Converter {
    fun celsiusToFahrenheit(celsius: Double): Double {
        return ((celsius * 9.0 / 5.0) + 32)
    }

    fun fahrenheitToCelsius(fahrenheit: Double): Double {
        return ((fahrenheit - 32) * 5.0/9.0)
    }
}