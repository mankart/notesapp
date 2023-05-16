package io.github.reskimulud.mynotesapp.data.network.response

import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(
    @field:SerializedName("accessToken")
    val accessToken: String,
)