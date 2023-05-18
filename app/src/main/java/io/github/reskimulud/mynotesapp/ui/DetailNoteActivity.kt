package io.github.reskimulud.mynotesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import io.github.reskimulud.mynotesapp.R
import io.github.reskimulud.mynotesapp.databinding.ActivityDetailNoteBinding
import io.github.reskimulud.mynotesapp.ui.viewmodel.DetailNoteViewModel
import io.github.reskimulud.mynotesapp.ui.viewmodel.ViewModelFactory

class DetailNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNoteBinding

    private lateinit var factory: ViewModelFactory
    private val detailNoteViewModel: DetailNoteViewModel by viewModels { factory }

    private var noteId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        factory = ViewModelFactory.getInstance(this)

        noteId = intent.getStringExtra(EXTRA_NOTE_ID)

        observables()
    }

    override fun onStart() {
        super.onStart()
        noteId?.let { detailNoteViewModel.getNoteById(it) }
    }

    private fun observables() {
        detailNoteViewModel.apply {
            note.observe(this@DetailNoteActivity) {
                binding.tvTitle.text = it.title
                binding.tvBody.text = it.body
            }
            message.observe(this@DetailNoteActivity) {
                Toast.makeText(this@DetailNoteActivity, it, Toast.LENGTH_SHORT).show()
            }
            isNoteDeleted.observe(this@DetailNoteActivity) {
                if (it) {
                    finish()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_update_note -> {
                val toUpdateNote = Intent(this@DetailNoteActivity, UpdateNoteActivity::class.java)
                noteId?.let { toUpdateNote.putExtra(EXTRA_NOTE_ID_TO_UPDATE, it) }
                startActivity(toUpdateNote)
                true
            }
            R.id.menu_delete_note -> {
                showConfirmationDialog {
                    noteId?.let { detailNoteViewModel.deleteNoteById(it) }
                }
                true
            }
            else -> false
        }
    }

    private fun showConfirmationDialog(positiveAction: () -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi Hapus Catatan")
        builder.setMessage("Yakin untuk menghapus catatan?")

        builder.setPositiveButton("Ya") { _, _ ->
            positiveAction()
        }

        builder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    companion object {
        const val EXTRA_NOTE_ID = "extra_note_id"
        const val EXTRA_NOTE_ID_TO_UPDATE = "extra_note_id_update"
    }
}