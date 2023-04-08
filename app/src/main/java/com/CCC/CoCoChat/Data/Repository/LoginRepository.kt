package com.CCC.CoCoChat.Data.Repository

import com.CCC.CoCoChat.Data.Request.LoginRequest
import com.CCC.CoCoChat.Data.Response.LoginResponse

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse>

    suspend fun logout()
}