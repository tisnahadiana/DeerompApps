package id.deeromptech.deerompapps.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import id.deeromptech.deerompapps.MainActivity
import id.deeromptech.deerompapps.databinding.ActivityLoginBinding
import id.deeromptech.deerompapps.model.User
import id.deeromptech.deerompapps.model.dataDummy
import id.deeromptech.deerompapps.utils.ViewBindingExt.viewBinding

class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)
    private var loginAttempts = 0
    private lateinit var user : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        user = dataDummy.firstOrNull() ?: User()

        binding.buttonLogin.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passEditText.text.toString().toIntOrNull()

            if (username == user.username && password == user.password) {
                openMainMenu()
            } else {
                loginAttempts++
                if (loginAttempts > 3) {
                    showResetPasswordDialog()
                } else {
                    showWrongCredentialsDialog()
                }
            }
        }

    }

    private fun openMainMenu() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    private fun showWrongCredentialsDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Wrong username or password")
            .setMessage("Please check your username and password and try again.")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun showResetPasswordDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Reset Password")
            .setMessage("You've reached the maximum login attempts. Would you like to reset your password?")
            .setPositiveButton("Reset") { _, _ ->
                Toast.makeText(this, "Cooming Soon", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

}