package id.deeromptech.deerompapps.view.conversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import id.deeromptech.deerompapps.databinding.ActivityConversionBinding
import id.deeromptech.deerompapps.utils.ViewBindingExt.viewBinding
import java.text.DecimalFormat

class ConversionActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityConversionBinding::inflate)
    private val temperatureTypes = arrayOf("Celsius ke Fahrenheit", "Fahrenheit ke Celsius")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, temperatureTypes)
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
                    else -> 0.0
                }

                val decimalFormat = DecimalFormat("#.##")
                binding.TVConversionValue.text = decimalFormat.format(convertedValue)
            }
        }
    }

    private fun showTemperatureTypeDialog() {
        if (binding.inputTypeConversion.text.isEmpty()) {
            val builder = MaterialAlertDialogBuilder(this)
            builder.setTitle("Pilih Tipe Konversi Suhu")
            builder.setItems(temperatureTypes) { _, which ->
                binding.inputTypeConversion.setText(temperatureTypes[which])
                convertTemperature()
            }
            builder.setCancelable(true)
            builder.show()
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

}