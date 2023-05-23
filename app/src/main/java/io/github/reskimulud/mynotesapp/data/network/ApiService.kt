package io.github.reskimulud.mynotesapp.data.network

import io.github.reskimulud.mynotesapp.data.network.response.*
import retrofit2.http.*

interface ApiService {
    //  TODO : Menambahkan seluruh method untuk meng-hit API

    // Register
    @POST("users")
    @FormUrlEncoded
    suspend fun postRegister(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("fullname") fullName: String
    ): ResponseWithoutData

    // Login
    @POST("authentications")
    suspend fun postLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): ResponseWithData<AuthenticationResponse>
}