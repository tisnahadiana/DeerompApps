package id.deeromptech.deerompapps.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.deeromptech.deerompapps.databinding.ActivityDashboardBinding
import id.deeromptech.deerompapps.utils.ViewBindingExt.viewBinding
import id.deeromptech.deerompapps.view.conversion.DistanceConversionActivity
import id.deeromptech.deerompapps.view.conversion.MasaConversionActivity
import id.deeromptech.deerompapps.view.conversion.TemperatureConversionActivity
import id.deeromptech.deerompapps.view.conversion.TimeConversionActivity

class DashboardActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityDashboardBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.menuSuhu.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, TemperatureConversionActivity::class.java))
        }

        binding.menuJarak.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, DistanceConversionActivity::class.java))
        }

        binding.menuMasa.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, MasaConversionActivity::class.java))
        }

        binding.menuWaktu.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, TimeConversionActivity::class.java))
        }

    }
}