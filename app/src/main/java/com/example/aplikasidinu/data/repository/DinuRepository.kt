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

}