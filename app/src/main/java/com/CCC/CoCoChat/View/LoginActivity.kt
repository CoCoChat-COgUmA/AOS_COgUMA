package com.CCC.CoCoChat.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.CCC.CoCoChat.App.Companion.pref
import com.CCC.CoCoChat.Data.Repository.User
import com.CCC.CoCoChat.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        pref.user = User("앙")

        Log.d("여기", pref.user.toString())
    }
}