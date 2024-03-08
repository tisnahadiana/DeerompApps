package id.deeromptech.deerompapps.conversionapp.view.conversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import id.deeromptech.deerompapps.databinding.ActivityDistanceConversionBinding
import id.deeromptech.deerompapps.utils.ViewBindingExt.viewBinding
import java.text.DecimalFormat

class DistanceConversionActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityDistanceConversionBinding::inflate)
    private val distanceTypes = arrayOf(
        "Kilometer ke Meter",
        "Meter ke Kilometer",
        "Mil ke Kilometer"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, distanceTypes)
        (binding.inputTypeConversion as? AppCompatAutoCompleteTextView)?.setAdapter(adapter)

        binding.inputJarak.addTextChangedListener {
            convertDistance()
        }

        binding.inputTypeConversion.setOnClickListener {
            showDistanceTypeDialog()
        }

        binding.TVConversionValue.text = "0"
    }

    private fun convertDistance() {
        val inputText = binding.inputJarak.text.toString()
        val inputValue = inputText.toDoubleOrNull()

        if (inputValue != null) {
            val selectedTypeIndex = binding.inputTypeConversion.text.toString().let { type ->
                distanceTypes.indexOf(type)
            }

            if (selectedTypeIndex != -1) {
                val convertedValue = when (selectedTypeIndex) {
                    0 -> convertKilometerToMeter(inputValue)
                    1 -> convertMeterToKilometer(inputValue)
                    2 -> convertMilesToKilometers(inputValue)
                    else -> 0.0
                }

                val decimalFormat = DecimalFormat("#.##")
                binding.TVConversionValue.text = decimalFormat.format(convertedValue)
            }
        }
    }

    private fun showDistanceTypeDialog() {
        if (binding.inputTypeConversion.text.isEmpty()) {
            Toast.makeText(this, "Pilih Tipe Konversi Jarak", Toast.LENGTH_SHORT).show()
        } else {
            convertDistance()
        }
    }

    private fun convertKilometerToMeter(value: Double): Double {
        return value * 1000
    }

    private fun convertMeterToKilometer(value: Double): Double {
        return value / 1000
    }

    private fun convertMilesToKilometers(value: Double): Double {
        return value * 1.60934
    }
}