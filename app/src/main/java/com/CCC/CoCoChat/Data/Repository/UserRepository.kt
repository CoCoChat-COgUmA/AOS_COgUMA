package com.CCC.CoCoChat.Data.Repository

import com.CCC.CoCoChat.App.Companion.pref
import com.CCC.CoCoChat.Data.Request.LoginRequest
import com.CCC.CoCoChat.Data.Response.LoginResponse
import com.CCC.CoCoChat.Data.Retrofit.Retrofit.getRetrofit
import com.CCC.CoCoChat.EncryptionAES.encrypt
import com.CCC.CoCoChat.EncryptionAES.generateSecretKey
import com.CCC.CoCoChat.EncryptionAES.loadKey
import com.CCC.CoCoChat.EncryptionAES.storeKey

open class UserRepository {
    private var user: User? = null
    private val loginIdAlias = "login_id_secret_key"
    private val loginPwAlias = "login_pw_secret_key"
    private val idSecretKey = loadKey(loginIdAlias)?:generateSecretKey()
    private val pwSecretKey = loadKey(loginPwAlias)?:generateSecretKey()

    private fun insertUser(user: User, id: String, pw: String) {
        this.user = user
        storeKey(loginIdAlias, idSecretKey)
        storeKey(loginPwAlias, pwSecretKey)
        if (pref.checkAutoLogin) {
            pref.userId = id
            pref.userPw = pw
        }
    }

    fun getNickname() = user?.nickname
    fun updateNickname(nickname: String) { user?.nickname = nickname }

    open suspend fun signIn(loginRequest: LoginRequest): Result<LoginResponse?> {
        val id = encrypt(loginRequest.id, idSecretKey)
        val pw = encrypt(loginRequest.pw, pwSecretKey)

        try {
            runCatching {
                getRetrofit().login(LoginRequest(id, pw))
            }.onSuccess {
                insertUser(it.user, id, pw)
                return Result.success(it)
            }.onFailure { throwable ->
                return Result.failure(throwable)
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.failure(Exception("Unknown error"))
    }

    fun signOut() {
        if (!pref.checkAutoLogin) {
            pref.userId = null
            pref.userPw = null
        }
        user = null
    }
}