package io.github.reskimulud.mynotesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.reskimulud.mynotesapp.databinding.ActivityDetailNoteBinding

class DetailNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}