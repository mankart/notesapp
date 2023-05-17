package io.github.reskimulud.mynotesapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.reskimulud.mynotesapp.R
import io.github.reskimulud.mynotesapp.data.local.LocalDataStore
import io.github.reskimulud.mynotesapp.databinding.ActivityMainBinding
import io.github.reskimulud.mynotesapp.ui.adapter.ListNotesAdapter
import io.github.reskimulud.mynotesapp.ui.viewmodel.MainViewModel
import io.github.reskimulud.mynotesapp.ui.viewmodel.ViewModelFactory

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = LocalDataStore.LOCAL_DATASTORE)
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

        factory = ViewModelFactory.getInstance(this)

        rvNotes = binding.rvNotes
        listNotesAdapter = ListNotesAdapter()

        showRecyclerList()
        observables()
    }

    private fun showRecyclerList() {
        rvNotes.layoutManager = LinearLayoutManager(this)
        rvNotes.adapter = listNotesAdapter

        mainViewModel.getNotes()
    }

    private fun observables() {
        mainViewModel.apply {
            isLoading.observe(this@MainActivity) {
                showLoadingIndicator(it)
            }
            message.observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
            notes.observe(this@MainActivity) {
                listNotesAdapter.setData(it)
                if (it.isEmpty()) {
                    showEmptyNotes(true)
                } else {
                    showEmptyNotes(false)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_logout -> {
                mainViewModel.logout()
                val intentToLogin = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intentToLogin)
                finish()
                true
            }
            else -> false
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