package io.github.reskimulud.mynotesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import io.github.reskimulud.mynotesapp.R
import io.github.reskimulud.mynotesapp.databinding.ActivityUpdateNoteBinding
import io.github.reskimulud.mynotesapp.model.Note
import io.github.reskimulud.mynotesapp.ui.viewmodel.UpdateNoteViewModel
import io.github.reskimulud.mynotesapp.ui.viewmodel.ViewModelFactory

class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNoteBinding

    private lateinit var factory: ViewModelFactory
    private val updateNoteViewModel: UpdateNoteViewModel by viewModels { factory }

    private var noteId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        factory = ViewModelFactory.getInstance()

        noteId = intent.getStringExtra(DetailNoteActivity.EXTRA_NOTE_ID_TO_UPDATE)

        val btnSave = binding.layoutUpdateNote.btnSave

        observables()

        btnSave.setOnClickListener {
            val etTitle = binding.layoutUpdateNote.etTitleNote.text
            val etBody = binding.layoutUpdateNote.etBodyNote.text

            // TODO : Ketika tombol Simpan ditekan (Memperbarui catatan)
        }

        supportActionBar?.title = "Ubah Catatan"
    }

    override fun onStart() {
        super.onStart()
        // TODO : Panggil method untuk mengambil data catatan
    }

    private fun observables() {
        updateNoteViewModel.apply {
            // TODO : method untuk observasi live data dari viewModel
        }
    }

    private fun setNoteDataToEditText(note: Note) {
        binding.layoutUpdateNote.apply {
            etTitleNote.setText(note.title)
            etBodyNote.setText(note.body)
        }
    }

    // Method untuk membuat menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update_menu, menu)
        return true
    }

    // Ketika salah satu menu dipilih
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_cancel_update -> {
                // ketika batal memperbarui/mengedit data
                finish()
                true
            }
            else -> false
        }
    }
}