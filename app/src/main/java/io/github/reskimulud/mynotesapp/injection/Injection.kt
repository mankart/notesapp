package io.github.reskimulud.mynotesapp.injection

import io.github.reskimulud.mynotesapp.data.NotesRepository
import io.github.reskimulud.mynotesapp.data.network.ApiConfig

object Injection {
    fun provideRepository(): NotesRepository {
        val apiService = ApiConfig.getApiService()
        return NotesRepository.getInstance(apiService)
    }
}