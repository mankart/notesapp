package io.github.reskimulud.mynotesapp.data

import io.github.reskimulud.mynotesapp.data.network.ApiService
import io.github.reskimulud.mynotesapp.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class NotesRepository(
    private val apiService: ApiService
) {
    // TODO : Menambahkan semua method yang berkaitan dengan CRUD

    companion object {
        @Volatile
        private var INSTANCE: NotesRepository? = null

        @JvmStatic
        fun getInstance(apiService: ApiService): NotesRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = NotesRepository(apiService)
                INSTANCE = instance
                instance
            }
        }
    }
}