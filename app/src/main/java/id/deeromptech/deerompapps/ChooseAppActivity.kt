package id.deeromptech.deerompapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.deeromptech.deerompapps.conversionapp.view.dashboard.DashboardConversionActivity
import id.deeromptech.deerompapps.databinding.ActivityChooseAppBinding
import id.deeromptech.deerompapps.newsapp.view.dashboard.DashboardNewsActivity
import id.deeromptech.deerompapps.utils.ViewBindingExt.viewBinding

class ChooseAppActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityChooseAppBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.cvConversionTemp.setOnClickListener {
            startActivity(Intent(this@ChooseAppActivity, DashboardConversionActivity::class.java))
        }

        binding.cvNewsApp.setOnClickListener {
            startActivity(Intent(this@ChooseAppActivity, DashboardNewsActivity::class.java))
        }
    }
}