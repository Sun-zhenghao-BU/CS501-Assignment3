package com.example.temperatureconverter

import org.junit.Assert.*
import org.junit.Test
import com.example.temperatureconverter.MainActivity
class MainActivityTest{

    private val converter = Converter()

    @Test
    fun testFahrenheitToCelsius() {

        val fahrenheitValue = 68.0

        val result = converter.fahrenheitToCelsius(fahrenheitValue)

        val expectedCelsius = 20.0
        assert(result == expectedCelsius)
    }

    @Test
    fun testCelsiusToFahrenheit() {

        val CelsiusValue = 20.0

        val result = converter.celsiusToFahrenheit(CelsiusValue)

        val expectedFahrenheit = 68.0
        assert(result == expectedFahrenheit)
    }
}