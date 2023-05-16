package io.github.reskimulud.mynotesapp.data.network.response

import com.google.gson.annotations.SerializedName

data class ResponseWithoutData(
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("message")
    val message: String
)
