package io.github.reskimulud.mynotesapp.data

import io.github.reskimulud.mynotesapp.data.network.ApiService
import io.github.reskimulud.mynotesapp.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class NotesRepository(
    private val apiService: ApiService
) {
    // TODO : Menambahkan semua method yang berkaitan dengan CRUD

    // Register
    suspend fun postRegister(username: String, password: String, fullName: String): Result<String> {
        return try {
            val response = apiService.postRegister(username, password, fullName)
            Result.success(response.message)
        } catch (error: Exception) {
            error.printStackTrace()
            Result.failure(error)
        }
    }

    // Login
    suspend fun postLogin(username: String, password: String): Result<String> {
        return try {
            val response = apiService.postLogin(username, password)
            Result.success(response.data.accessToken)
        } catch (error: Exception) {
            error.printStackTrace()
            Result.failure(error)
        }
    }

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