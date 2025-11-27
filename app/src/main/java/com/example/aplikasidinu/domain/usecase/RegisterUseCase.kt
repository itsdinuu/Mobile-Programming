package com.example.aplikasidinu.domain.RegisterUseCase

import com.example.aplikasidinu.data.repository.DinuRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repo: DinuRepository
) {
    suspend operator fun invoke(username: String, full_name: String, date_of_birth: String, password: String): Result<String?>{
        return try {
            val response = repo.register(username, full_name, date_of_birth, password)

            if (response.status.equals("success")) {
                Result.success(response.message)
            } else {
                Result.failure(Exception(response.message))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}