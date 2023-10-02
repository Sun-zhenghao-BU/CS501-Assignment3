package com.example.temperatureconverter

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var seekBarCelsius : SeekBar;
    private lateinit var seekBarFahrenheit : SeekBar;

    private lateinit var textViewCelsius : TextView;
    private lateinit var textViewFahrenheit : TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarCelsius = findViewById(R.id.seekBar_C)
        seekBarFahrenheit = findViewById(R.id.seekBar_F)

        textViewCelsius = findViewById(R.id.Tv_C_show)
        textViewFahrenheit = findViewById(R.id.Tv_F_show)

        seekBarCelsius.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val fahrenheitValue = celsiusToFahrenheit(progress)
                    seekBarFahrenheit.progress = fahrenheitValue;
                    textViewCelsius.text = "$progress"
                    textViewFahrenheit.text = "$fahrenheitValue"
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        seekBarFahrenheit.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val celsiusValue = fahrenheitToCelsius(progress)
                    seekBarCelsius.progress = celsiusValue;
                    textViewFahrenheit.text = "$progress"
                    textViewCelsius.text = "$celsiusValue"
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    private fun celsiusToFahrenheit(celsius: Int): Int {
        return ((celsius * 9.0 / 5.0) + 32).toInt()
    }

    private fun fahrenheitToCelsius(fahrenheit: Int): Int {
        return ((fahrenheit - 32) * 5.0/9.0).toInt()
    }
}