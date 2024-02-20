package id.deeromptech.deerompapps.view.conversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import id.deeromptech.deerompapps.databinding.ActivityTimeConversionBinding
import id.deeromptech.deerompapps.utils.ViewBindingExt.viewBinding
import java.text.DecimalFormat

class TimeConversionActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityTimeConversionBinding::inflate)
    private val timeTypes = arrayOf(
        "Detik ke Menit",
        "Menit ke Detik",
        "Jam ke Menit",
        "Menit ke Jam"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, timeTypes)
        (binding.inputTypeConversion as? AppCompatAutoCompleteTextView)?.setAdapter(adapter)

        binding.inputWaktu.addTextChangedListener {
            convertDistance()
        }

        binding.inputTypeConversion.setOnClickListener {
            showDistanceTypeDialog()
        }

        binding.TVConversionValue.text = "0"
    }

    private fun convertDistance() {
        val inputText = binding.inputWaktu.text.toString()
        val inputValue = inputText.toDoubleOrNull()

        if (inputValue != null) {
            val selectedTypeIndex = binding.inputTypeConversion.text.toString().let { type ->
                timeTypes.indexOf(type)
            }

            if (selectedTypeIndex != -1) {
                val convertedValue = when (selectedTypeIndex) {
                    0 -> inputValue / 60
                    1 -> inputValue * 60
                    2 -> inputValue * 60
                    3 -> inputValue / 60
                    else -> 0.0
                }

                val decimalFormat = DecimalFormat("#.##")
                binding.TVConversionValue.text = decimalFormat.format(convertedValue)
            }
        }
    }

    private fun showDistanceTypeDialog() {
        if (binding.inputTypeConversion.text.isEmpty()) {
            Toast.makeText(this, "Pilih Tipe Konversi Waktu", Toast.LENGTH_SHORT).show()
        } else {
            convertDistance()
        }
    }
}