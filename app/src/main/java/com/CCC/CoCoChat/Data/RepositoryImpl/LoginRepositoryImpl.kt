package com.CCC.CoCoChat.Data.RepositoryImpl

import com.CCC.CoCoChat.Data.Repository.LoginRepository
import com.CCC.CoCoChat.Data.Request.LoginRequest
import com.CCC.CoCoChat.Data.Response.LoginResponse
import com.CCC.CoCoChat.Data.Retrofit.Retrofit.userRetrofit

class LoginRepositoryImpl : LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return try {
            val response = userRetrofit.login(loginRequest)
            when {
                response.isSuccessful -> Result.success(response.body()!!)
                response.body() == null -> Result.failure(Exception("Unknown error"))
                else -> Result.failure(Exception("Failed to login"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {

    }
}