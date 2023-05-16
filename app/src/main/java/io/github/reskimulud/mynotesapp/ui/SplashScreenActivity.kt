package io.github.reskimulud.mynotesapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import io.github.reskimulud.mynotesapp.data.local.LocalDataStore
import io.github.reskimulud.mynotesapp.databinding.ActivitySplashScreenBinding
import io.github.reskimulud.mynotesapp.ui.viewmodel.SplashScreenViewModel
import io.github.reskimulud.mynotesapp.ui.viewmodel.ViewModelFactory

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    private lateinit var factory: ViewModelFactory
    private val splashScreenViewModel: SplashScreenViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        factory = ViewModelFactory.getInstance(this)
        setupView()

        splashScreenViewModel.getUserToken().observe(this) {
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    if (it != LocalDataStore.EMPTY_TOKEN) {
                        // jika di data store terdapat token (sudah login), maka langsung ke main activity
                        val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        /// jika belum ada token (belum login), maka di arahkan ke login activity
                        val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    finish()
                }, 2000L)
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()

    }
}