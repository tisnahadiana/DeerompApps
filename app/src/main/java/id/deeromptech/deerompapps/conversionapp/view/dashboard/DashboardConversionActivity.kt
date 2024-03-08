package id.deeromptech.deerompapps.conversionapp.view.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.deeromptech.deerompapps.utils.ViewBindingExt.viewBinding
import id.deeromptech.deerompapps.conversionapp.view.conversion.DistanceConversionActivity
import id.deeromptech.deerompapps.conversionapp.view.conversion.MasaConversionActivity
import id.deeromptech.deerompapps.conversionapp.view.conversion.TemperatureConversionActivity
import id.deeromptech.deerompapps.conversionapp.view.conversion.TimeConversionActivity
import id.deeromptech.deerompapps.databinding.ActivityConversionDashboardBinding

class DashboardConversionActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityConversionDashboardBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.menuSuhu.setOnClickListener {
            startActivity(Intent(this@DashboardConversionActivity, TemperatureConversionActivity::class.java))
        }

        binding.menuJarak.setOnClickListener {
            startActivity(Intent(this@DashboardConversionActivity, DistanceConversionActivity::class.java))
        }

        binding.menuMasa.setOnClickListener {
            startActivity(Intent(this@DashboardConversionActivity, MasaConversionActivity::class.java))
        }

        binding.menuWaktu.setOnClickListener {
            startActivity(Intent(this@DashboardConversionActivity, TimeConversionActivity::class.java))
        }

    }
}