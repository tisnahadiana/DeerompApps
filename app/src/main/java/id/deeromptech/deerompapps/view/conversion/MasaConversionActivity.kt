package id.deeromptech.deerompapps.view.conversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import id.deeromptech.deerompapps.databinding.ActivityMasaConversionBinding
import id.deeromptech.deerompapps.utils.ViewBindingExt.viewBinding
import java.text.DecimalFormat

class MasaConversionActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMasaConversionBinding::inflate)
    private val masaTypes = arrayOf(
        "Kilogram ke Gram",
        "Gram ke Kilogram",
        "Kilogram ke Miligram",
        "Miligram ke Kilogram"
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, masaTypes)
        (binding.inputTypeConversion as? AppCompatAutoCompleteTextView)?.setAdapter(adapter)

        binding.inputMasa.addTextChangedListener {
            convertDistance()
        }

        binding.inputTypeConversion.setOnClickListener {
            showDistanceTypeDialog()
        }

        binding.TVConversionValue.text = "0"
    }

    private fun convertDistance() {
        val inputText = binding.inputMasa.text.toString()
        val inputValue = inputText.toDoubleOrNull()

        if (inputValue != null) {
            val selectedTypeIndex = binding.inputTypeConversion.text.toString().let { type ->
                masaTypes.indexOf(type)
            }

            if (selectedTypeIndex != -1) {
                val convertedValue = when (selectedTypeIndex) {
                    0 -> convertKilogramToGram(inputValue)
                    1 -> convertGramToKilogram(inputValue)
                    2 -> convertKilogramToMiligram(inputValue)
                    3 -> convertMiligramToKilogram(inputValue)
                    else -> 0.0
                }

                val decimalFormat = DecimalFormat("#.##")
                binding.TVConversionValue.text = decimalFormat.format(convertedValue)
            }
        }
    }

    private fun showDistanceTypeDialog() {
        if (binding.inputTypeConversion.text.isEmpty()) {
            Toast.makeText(this, "Pilih Tipe Konversi Masa", Toast.LENGTH_SHORT).show()
        } else {
            convertDistance()
        }
    }

    private fun convertKilogramToGram(value: Double): Double {
        return value * 1000
    }

    private fun convertGramToKilogram(value: Double): Double {
        return value / 1000
    }

    private fun convertKilogramToMiligram(value: Double): Double {
        return value * 1_000_000
    }

    private fun convertMiligramToKilogram(value: Double): Double {
        return value / 1_000_000
    }
}