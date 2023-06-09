package io.github.reskimulud.mynotesapp.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private fun getClient(): OkHttpClient {
        // TODO: Membuat client
        return OkHttpClient.Builder()
            .build()
    }

    fun getApiService(): ApiService {
        /// TODO: Membuat ApiService dengan Retrofit
        val retrofit = Retrofit.Builder()
            .build()
        return retrofit.create(ApiService::class.java)
    }
}