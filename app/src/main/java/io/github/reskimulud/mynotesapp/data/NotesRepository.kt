package io.github.reskimulud.mynotesapp.data

import io.github.reskimulud.mynotesapp.data.local.LocalDataStore
import io.github.reskimulud.mynotesapp.data.network.ApiService
import io.github.reskimulud.mynotesapp.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class NotesRepository(
    private val apiService: ApiService,
    private val dataStore: LocalDataStore
) {
    suspend fun saveUserToken(token: String) {
        return dataStore.saveUserToken(token)
    }

    fun getUserToken(): Flow<String> {
        return dataStore.getUserToken()
    }

    suspend fun clearCache() {
        return dataStore.clearCache()
    }

    private suspend fun setAuthorization(): String {
        val token = getUserToken().first()
        return StringBuilder("Bearer ").append(token).toString()
    }

    suspend fun postRegister(username: String, password: String, fullName: String): Result<String> {
        return try {
            val response = apiService.postRegister(username, password, fullName)
            Result.success(response.message)
        } catch (error: Exception) {
            error.printStackTrace()
            Result.failure(error)
        }
    }

    suspend fun postLogin(username: String, password: String): Result<String> {
        return try {
            val response = apiService.postLogin(username, password)
            Result.success(response.data.accessToken)
        } catch (error: Exception) {
            error.printStackTrace()
            Result.failure(error)
        }
    }

    suspend fun postNote(title: String, body: String): Result<String> {
        return try {
            val token = setAuthorization()
            val response = apiService.postNote(token, title, body)
            Result.success(response.message)
        } catch (error: Exception) {
            error.printStackTrace()
            Result.failure(error)
        }
    }

    suspend fun getNotes(): Result<List<Note>> {
        return try {
            val token = setAuthorization()
            val response = apiService.getNotes(token)
            Result.success(response.data.notes)
        } catch (error: Exception) {
            error.printStackTrace()
            Result.failure(error)
        }
    }

    suspend fun getNoteById(noteId: String): Result<Note> {
        return try {
            val token = setAuthorization()
            val response = apiService.getNoteById(token, noteId)
            Result.success(response.data.note)
        } catch (error: Exception) {
            error.printStackTrace()
            Result.failure(error)
        }
    }

    suspend fun deleteNoteById(noteId: String): Result<String> {
        return try {
            val token = setAuthorization()
            val response = apiService.deleteNoteById(token, noteId)
            Result.success(response.message)
        } catch (error: Exception) {
            error.printStackTrace()
            Result.failure(error)
        }
    }

    suspend fun putNoteById(noteId: String, title: String, body: String): Result<String> {
        return try {
            val token = setAuthorization()
            val response = apiService.putNoteById(token, noteId, title, body)
            Result.success(response.message)
        } catch (error: Exception) {
            error.printStackTrace()
            Result.failure(error)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: NotesRepository? = null

        @JvmStatic
        fun getInstance(apiService: ApiService, dataStore: LocalDataStore): NotesRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = NotesRepository(apiService, dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}