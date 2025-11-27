package com.example.aplikasidinu.data.remote.api

import com.example.aplikasidinu.data.remote.response.BaseResponse
import com.example.aplikasidinu.data.remote.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DinuApi {

    @FormUrlEncoded
    @POST("auth/login.php")

    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ) : BaseResponse<LoginResponse>
}