package com.example.aplikasidinu.data.remote.response

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: User?
) {
    data class User(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("image")
        val image: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("username")
        val username: String?,
        val token: String
    )
}

