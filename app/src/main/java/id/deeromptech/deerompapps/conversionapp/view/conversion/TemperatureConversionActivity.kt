package id.deeromptech.deerompapps.conversionapp.view.conversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import id.deeromptech.deerompapps.databinding.ActivityTemperatureConversionBinding
import id.deeromptech.deerompapps.utils.ViewBindingExt.viewBinding
import java.text.DecimalFormat

class TemperatureConversionActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityTemperatureConversionBinding::inflate)
    private val temperatureTypes = arrayOf(
        "Celsius ke Fahrenheit",
        "Fahrenheit ke Celsius",
        "Celsius ke Kelvin",
        "Kelvin ke Celsius",
        "Fahrenheit ke Kelvin",
        "Kelvin ke Fahrenheit"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, temperatureTypes)
        (binding.inputTypeConversion as? AppCompatAutoCompleteTextView)?.setAdapter(adapter)

        binding.inputSuhu.addTextChangedListener {
            convertTemperature()
        }

        binding.inputTypeConversion.setOnClickListener {
            showTemperatureTypeDialog()
        }

        binding.TVConversionValue.text = "0"
    }

    private fun convertTemperature() {
        val inputText = binding.inputSuhu.text.toString()
        val inputValue = inputText.toDoubleOrNull()

        if (inputValue != null) {
            val selectedTypeIndex = binding.inputTypeConversion.text.toString().let { type ->
                temperatureTypes.indexOf(type)
            }

            if (selectedTypeIndex != -1) {
                val convertedValue = when (selectedTypeIndex) {
                    0 -> convertCelsiusToFahrenheit(inputValue)
                    1 -> convertFahrenheitToCelsius(inputValue)
                    2 -> convertCelsiusToKelvin(inputValue)
                    3 -> convertKelvinToCelsius(inputValue)
                    4 -> convertFahrenheitToKelvin(inputValue)
                    5 -> convertKelvinToFahrenheit(inputValue)
                    else -> 0.0
                }

                val decimalFormat = DecimalFormat("#.##")
                binding.TVConversionValue.text = decimalFormat.format(convertedValue)
            }
        }
    }

    private fun showTemperatureTypeDialog() {
        if (binding.inputTypeConversion.text.isEmpty()) {
            Toast.makeText(this, "Pilih Tipe Konversi Suhu", Toast.LENGTH_SHORT).show()
        } else {
            convertTemperature()
        }
    }

    private fun convertCelsiusToFahrenheit(celsius: Double): Double {
        return celsius * 9 / 5 + 32
    }

    private fun convertFahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }

    private fun convertCelsiusToKelvin(celsius: Double): Double {
        return celsius + 273.15
    }

    private fun convertKelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }

    private fun convertFahrenheitToKelvin(fahrenheit: Double): Double {
        return (fahrenheit + 459.67) * 5 / 9
    }

    private fun convertKelvinToFahrenheit(kelvin: Double): Double {
        return kelvin * 9 / 5 - 459.67
    }

}