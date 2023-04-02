package com.CCC.CoCoChat.View

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.preferencesDataStore
import com.CCC.CoCoChat.R

class SplashActivity : AppCompatActivity() {
    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
    }

    val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }
}