package com.CCC.CoCoChat.Data.RepositoryImpl

import com.CCC.CoCoChat.Data.Repository.User
import com.CCC.CoCoChat.Data.Repository.UserRepository
import com.CCC.CoCoChat.Data.Request.LoginRequest
import com.CCC.CoCoChat.Data.Response.LoginResponse

class UserRepositoryImpl() : UserRepository() {

    override suspend fun signIn(loginRequest: LoginRequest): Result<LoginResponse?> {}
}