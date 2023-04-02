package com.CCC.CoCoChat

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.CCC.CoCoChat.Data.Preference.SharedPreferences
import com.CCC.CoCoChat.Data.Retrofit.Retrofit.init

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