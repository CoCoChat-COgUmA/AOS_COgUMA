package com.CCC.CoCoChat.Data.Response

import android.os.Parcelable
import com.CCC.CoCoChat.Data.Module.User
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    @SerializedName("result")
    val result: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("user")
    val user: User
): Parcelable