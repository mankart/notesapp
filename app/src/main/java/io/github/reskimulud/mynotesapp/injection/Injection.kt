package io.github.reskimulud.mynotesapp.injection

import android.content.Context
import io.github.reskimulud.mynotesapp.data.NotesRepository
import io.github.reskimulud.mynotesapp.data.local.LocalDataStore
import io.github.reskimulud.mynotesapp.data.network.ApiConfig
import io.github.reskimulud.mynotesapp.ui.dataStore

object Injection {
    fun provideRepository(context: Context): NotesRepository {
        val apiService = ApiConfig.getApiService()
        val dataStore = LocalDataStore.getInstance(context.dataStore)
        return NotesRepository.getInstance(apiService, dataStore)
    }
}