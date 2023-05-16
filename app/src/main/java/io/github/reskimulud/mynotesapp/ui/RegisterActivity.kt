package io.github.reskimulud.mynotesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import io.github.reskimulud.mynotesapp.databinding.ActivityRegisterBinding
import io.github.reskimulud.mynotesapp.ui.viewmodel.RegisterViewModel
import io.github.reskimulud.mynotesapp.ui.viewmodel.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private lateinit var factory: ViewModelFactory
    private val registerViewModel: RegisterViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        factory = ViewModelFactory.getInstance(this)

        val fullName = binding.etFullName.text
        val username = binding.etUsername.text
        val password = binding.etPassword.text

        observables()

        binding.btnRegister.setOnClickListener {
            Log.e("Register", "Register di klik $username $password $fullName")
            if (!username.isNullOrEmpty() && !password.isNullOrEmpty() && !fullName.isNullOrEmpty()) {
                Log.e("Register", "Kondisi true")
                registerViewModel.register(username.trim().toString(), password.trim().toString(), fullName.trim().toString())
            }
        }

        binding.tvToLogin.setOnClickListener {
            val intentToLogin = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intentToLogin)
            finish()
        }
    }

    private fun observables() {
        registerViewModel.apply {
            message.observe(this@RegisterActivity) {
                Toast.makeText(this@RegisterActivity, it, Toast.LENGTH_SHORT).show()
            }
            isLoading.observe(this@RegisterActivity) {
                showLoadingIndicator(it)
            }
            isSuccess.observe(this@RegisterActivity) {
                if (it) {
                    val intentToLogin = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intentToLogin)
                    finish()
                }
            }
        }
    }

    private fun showLoadingIndicator(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                pbLoading.visibility = View.VISIBLE
                etUsername.isEnabled = false
                etPassword.isEnabled = false
                etFullName.isEnabled = false
                btnRegister.isEnabled = false
            } else {
                pbLoading.visibility = View.GONE
                etUsername.isEnabled = true
                etPassword.isEnabled = true
                etFullName.isEnabled = true
                btnRegister.isEnabled = true
            }
        }
    }
}