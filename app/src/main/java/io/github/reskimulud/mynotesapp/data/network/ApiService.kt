package io.github.reskimulud.mynotesapp.data.network

import io.github.reskimulud.mynotesapp.data.network.response.*
import retrofit2.http.*

interface ApiService {
    @POST("users")
    @FormUrlEncoded
    suspend fun postRegister(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("fullname") fullName: String
    ): ResponseWithoutData

    @POST("authentications")
    @FormUrlEncoded
    suspend fun postLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): ResponseWithData<AuthenticationResponse>

    @POST("notes")
    @FormUrlEncoded
    suspend fun postNote(
        @Header("Authorization") token: String,
        @Field("title") title: String,
        @Field("body") body: String
    ): ResponseWithoutData

    @GET("notes")
    suspend fun getNotes(
        @Header("Authorization") token: String
    ): ResponseWithData<NotesResponse>

    @GET("notes/{id}")
    suspend fun getNoteById(
        @Header("Authorization") token: String,
        @Path("id") noteId: String
    ): ResponseWithData<NoteResponse>

    @PUT("notes/{id}")
    @FormUrlEncoded
    suspend fun putNoteById(
        @Header("Authorization") token: String,
        @Path("id") noteId: String,
        @Field("title") title: String,
        @Field("body") body: String
    ): ResponseWithoutData

    @DELETE("notes/{id}")
    suspend fun deleteNoteById(
        @Header("Authorization") token: String,
        @Path("id") noteId: String
    ): ResponseWithoutData

}