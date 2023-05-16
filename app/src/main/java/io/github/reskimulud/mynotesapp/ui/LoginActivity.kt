package io.github.reskimulud.mynotesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import io.github.reskimulud.mynotesapp.databinding.ActivityLoginBinding
import io.github.reskimulud.mynotesapp.ui.viewmodel.LoginViewModel
import io.github.reskimulud.mynotesapp.ui.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var factory: ViewModelFactory
    private val loginViewModel: LoginViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        factory = ViewModelFactory.getInstance(this)

        val username = binding.etUsername.text
        val password = binding.etPassword.text
        val btnLogin = binding.btnLogin

        observables()

        btnLogin.setOnClickListener {
            if (!username.isNullOrEmpty() && !password.isNullOrEmpty()) {
                loginViewModel.login(username.trim().toString(), password.trim().toString())
            }
        }

        binding.tvToRegister.setOnClickListener {
            val intentToRegister = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intentToRegister)
            finish()
        }
    }

    private fun observables() {
        loginViewModel.apply {
            token.observe(this@LoginActivity) {
                loginViewModel.saveToken(it)
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            isLoading.observe(this@LoginActivity) {
                showLoadingIndicator(it)
            }
            message.observe(this@LoginActivity) {
                Toast.makeText(this@LoginActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoadingIndicator(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                pbLoading.visibility = View.VISIBLE
                etUsername.isEnabled = false
                etPassword.isEnabled = false
                btnLogin.isEnabled = false
            } else {
                pbLoading.visibility = View.GONE
                etUsername.isEnabled = true
                etPassword.isEnabled = true
                btnLogin.isEnabled = true
            }
        }
    }
}