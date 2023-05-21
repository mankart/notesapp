package io.github.reskimulud.mynotesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import io.github.reskimulud.mynotesapp.databinding.ActivityAddNoteBinding
import io.github.reskimulud.mynotesapp.ui.viewmodel.AddNoteViewModel
import io.github.reskimulud.mynotesapp.ui.viewmodel.ViewModelFactory

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding


    private lateinit var factory: ViewModelFactory
    private val addNoteViewModel: AddNoteViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        factory = ViewModelFactory.getInstance()

        val btnSave = binding.layoutAddNote.btnSave

        observables()

        btnSave.setOnClickListener {
            val etTitle = binding.layoutAddNote.etTitleNote.text
            val etBody = binding.layoutAddNote.etBodyNote.text

            // TODO : Ketika tombool Simpan ditekan (Membuat catatan baru)
        }

        supportActionBar?.title = "Tambah Catatan"
    }

    private fun observables() {
        addNoteViewModel.apply {
            // TODO : method untuk observasi live data dari viewModel
        }
    }
}