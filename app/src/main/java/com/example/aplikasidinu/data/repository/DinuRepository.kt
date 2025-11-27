package com.example.aplikasidinu.data.repository

import com.example.aplikasidinu.data.remote.api.DinuApi
import com.example.aplikasidinu.data.remote.response.BaseResponse
import com.example.aplikasidinu.data.remote.response.LoginResponse
import javax.inject.Inject

class DinuRepository @Inject constructor(
    private val dinuApi: DinuApi
) {

    suspend fun login(username: String, password: String): BaseResponse<LoginResponse> {
        return dinuApi.login(username, password)
    }

    suspend fun register(username: String, full_name: String, date_of_birth: String, password: String): BaseResponse<Any> {
        return dinuApi.register(username, full_name, date_of_birth, password)
    }

}