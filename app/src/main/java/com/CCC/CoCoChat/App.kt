package com.CCC.CoCoChat

import android.app.Application
import com.CCC.CoCoChat.Data.Preference.SharedPreferences
import com.CCC.CoCoChat.Data.Retrofit.Retrofit.init
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    companion object {
        lateinit var pref: SharedPreferences
    }

    override fun onCreate() {
        pref = SharedPreferences(applicationContext)
        init(pref.pref)
        super.onCreate()
    }
}