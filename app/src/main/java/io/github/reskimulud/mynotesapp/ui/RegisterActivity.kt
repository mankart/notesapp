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

        factory = ViewModelFactory.getInstance()

        observables()

        binding.btnRegister.setOnClickListener {
            // TODO : Ketika tombol register ditekan
            val fullName = binding.etFullName.text
            val username = binding.etUsername.text
            val password = binding.etPassword.text

            if (!fullName.isNullOrEmpty() && !username.isNullOrEmpty() && !password.isNullOrEmpty()) {
                registerViewModel.register(
                    username.toString().trim(),
                    password.toString().trim(),
                    fullName.toString().trim()
                )
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
            // TODO : Method untuk mengobservasi LiveData dari ViewModel
            isSuccess.observe(this@RegisterActivity) {
                if (it) {
                    // jika register berhasil
                    // pindah ke activity login
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            message.observe(this@RegisterActivity) {
                Toast.makeText(this@RegisterActivity, it, Toast.LENGTH_SHORT).show()
            }
            isLoading.observe(this@RegisterActivity) {
                showLoadingIndicator(it)
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