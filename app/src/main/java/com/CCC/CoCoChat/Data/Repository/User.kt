package com.CCC.CoCoChat.Data.Repository

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("nickname")
    var nickname: String
)
