package io.github.reskimulud.mynotesapp.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataStore(private val dataStore: DataStore<Preferences>) {
    suspend fun saveUserToken(token: String) {
        dataStore.edit {
            it[USER_TOKEN_KEY] = token
        }
    }

    fun getUserToken(): Flow<String> {
        return dataStore.data.map {
            it[USER_TOKEN_KEY] ?: EMPTY_TOKEN
        }
    }

    suspend fun clearCache() {
        dataStore.edit {
            it.clear()
        }
    }

    companion object {
        const val LOCAL_DATASTORE = "local_datastore"
        const val EMPTY_TOKEN = "empty_token"
        private val USER_TOKEN_KEY = stringPreferencesKey("user_token")

        @Volatile
        private var INSTANCE: LocalDataStore? = null

        @JvmStatic
        fun getInstance(dataStore: DataStore<Preferences>): LocalDataStore {
            return INSTANCE ?: synchronized(this) {
                val instance = LocalDataStore(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}