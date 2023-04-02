package com.CCC.CoCoChat.Data.Response

import com.CCC.CoCoChat.Data.Repository.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("result")
    val result: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("user")
    val user: User
)