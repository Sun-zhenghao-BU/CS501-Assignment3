package com.example.temperatureconverter

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var seekBarCelsius : SeekBar;
    private lateinit var seekBarFahrenheit : SeekBar;

    private lateinit var textViewCelsius : TextView;
    private lateinit var textViewFahrenheit : TextView;

    private var wasBelow20: Boolean? = null
    private var converter = Converter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarCelsius = findViewById(R.id.seekBar_C)
        seekBarFahrenheit = findViewById(R.id.seekBar_F)

        textViewCelsius = findViewById(R.id.Tv_C_show)
        textViewFahrenheit = findViewById(R.id.Tv_F_show)
        textViewFahrenheit.text = String.format("%.2f°F", 32.00)
        textViewCelsius.text = String.format("%.2f°C", 0.00)

        seekBarCelsius.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val fahrenheitValue = converter.celsiusToFahrenheit(progress.toDouble())
                    seekBarFahrenheit.progress = fahrenheitValue.toInt()
                    textViewCelsius.text = String.format("%.2f°C", progress.toDouble())
                    textViewFahrenheit.text = String.format("%.2f°F", fahrenheitValue)

                    if (progress <= 20 && (wasBelow20 == null || !wasBelow20!!)) {
                        showCenteredSnackBar(seekBar, "I wish it were warmer.", Snackbar.LENGTH_SHORT)
                        wasBelow20 = true
                    } else if (progress > 20 && (wasBelow20 == null || wasBelow20!!)) {
                        showCenteredSnackBar(seekBar, "I wish it were colder.", Snackbar.LENGTH_SHORT)
                        wasBelow20 = false
                    }
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
                    val celsiusValue = converter.fahrenheitToCelsius(progress.toDouble())
                    seekBarCelsius.progress = celsiusValue.toInt()
                    textViewFahrenheit.text = String.format("%.2f°F", progress.toDouble())
                    textViewCelsius.text = String.format("%.2f°C", celsiusValue)

                    if (celsiusValue <= 20 && (wasBelow20 == null || !wasBelow20!!)) {
                        showCenteredSnackBar(seekBar, "I wish it were warmer.", Snackbar.LENGTH_SHORT)
                        wasBelow20 = true
                    } else if (celsiusValue > 20 && (wasBelow20 == null || wasBelow20!!)) {
                        showCenteredSnackBar(seekBar, "I wish it were colder.", Snackbar.LENGTH_SHORT)
                        wasBelow20 = false
                    }
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if ((seekBar?.progress ?: 0) < 32) {
                    seekBar?.progress = 32
                    textViewFahrenheit.text = String.format("%.2f°F", 32.0)
                    textViewCelsius.text = String.format("%.2f°C", 0.0)
                }
            }
        })
    }

//    fun celsiusToFahrenheit(celsius: Double): Double {
//        return ((celsius * 9.0 / 5.0) + 32)
//    }
//
//    fun fahrenheitToCelsius(fahrenheit: Double): Double {
//        return ((fahrenheit - 32) * 5.0/9.0)
//    }

    private fun showCenteredSnackBar(view: View, message: String, duration: Int) {
        val snackbar = Snackbar.make(view, message, duration)

        val textView = snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
        snackbar.show()
    }
}