package com.CCC.CoCoChat.Data.Request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("pw")
    val pw: String
)