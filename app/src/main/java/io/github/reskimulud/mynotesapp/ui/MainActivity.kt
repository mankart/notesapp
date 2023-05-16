package io.github.reskimulud.mynotesapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.reskimulud.mynotesapp.databinding.ActivityMainBinding
import io.github.reskimulud.mynotesapp.ui.adapter.ListNotesAdapter
import io.github.reskimulud.mynotesapp.ui.viewmodel.MainViewModel
import io.github.reskimulud.mynotesapp.ui.viewmodel.ViewModelFactory
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var factory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { factory }

    private lateinit var rvNotes: RecyclerView
    private lateinit var listNotesAdapter: ListNotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        factory = ViewModelFactory.getInstance()

        rvNotes = binding.rvNotes
        listNotesAdapter = ListNotesAdapter()

        showRecyclerList()
        observables()

        // TODO : Sementara tampilkan empty notes
        showEmptyNotes(true)
    }

    private fun showRecyclerList() {
        rvNotes.layoutManager = LinearLayoutManager(this)
        rvNotes.adapter = listNotesAdapter

        // TODO : Panggil method untuk mendapatkan data catatan
    }

    private fun observables() {
        mainViewModel.apply {
            // TODO : Method untuk mengobservasi LiveData dari ViewModel
        }
    }

    private fun showLoadingIndicator(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                pbLoading.visibility = View.VISIBLE
            } else {
                pbLoading.visibility = View.GONE
            }
        }
    }

    private fun showEmptyNotes(isShow: Boolean) {
        if (isShow) {
            rvNotes.visibility = View.GONE
            binding.tvEmptyList.visibility = View.VISIBLE
        } else {
            rvNotes.visibility = View.VISIBLE
            binding.tvEmptyList.visibility = View.GONE
        }
    }
}