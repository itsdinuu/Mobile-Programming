package com.example.aplikasidinu.domain.usecase

import com.example.aplikasidinu.data.mapper.toUserDomain
import com.example.aplikasidinu.data.remote.response.LoginResponse
import com.example.aplikasidinu.data.repository.DinuRepository
import javax.inject.Inject
import kotlin.Result.Companion.failure


class LoginUseCase @Inject constructor(
    private val repo: DinuRepository
) {
    suspend operator fun invoke(username: String, password: String) : Result<LoginResponse.User> {
        return try {
            val response = repo.login(username, password)
            if (response.status.equals("success") && response.data != null) {
                val user = response.data.toUserDomain()
                Result.success(user)
            } else {
                failure(Exception(response.message))
            }
        } catch (e: Exception) {
            failure(e)

      }
    }

}