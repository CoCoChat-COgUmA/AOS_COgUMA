package com.CCC.CoCoChat.Data.Module

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("userId")
    var userId: String,
    @SerializedName("userName")
    var userName: String
): Parcelable
