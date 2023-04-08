package com.CCC.CoCoChat.Data.Preference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.CCC.CoCoChat.Data.Preference.PreferenceKey.CHECK_AUTO_LOGIN
import com.CCC.CoCoChat.Data.Preference.PreferenceKey.USER_ID
import com.CCC.CoCoChat.Data.Preference.PreferenceKey.USER_PW

class SharedPreferences(context: Context) {
    val pref = context.getSharedPreferences(context.packageName, MODE_PRIVATE)


    var checkAutoLogin: Boolean
        get() {
            return pref.getBoolean(CHECK_AUTO_LOGIN, false)
        }
        set(value) {
            pref.edit().putBoolean(CHECK_AUTO_LOGIN, value).apply()
        }

    var userId: String?
        get() {
            return pref.getString(USER_ID, null)
        }
        set(value) {
            pref.edit()!!.putString(USER_ID, value).apply()
        }

    var userPw: String?
        get() {
            return pref.getString(USER_PW, null)
        }
        set(value) {
            pref.edit()!!.putString(USER_PW, value).apply()
        }
}