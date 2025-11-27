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

    @FormUrlEncoded
    @POST("auth/register.php")

    suspend fun register(
        @Field("username") username: String,
        @Field("full_name") full_name: String,
        @Field("date_of_birth") date_of_birth: String,
        @Field("password") password: String,
    ): BaseResponse<Any>
}