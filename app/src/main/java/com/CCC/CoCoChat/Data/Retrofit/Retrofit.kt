package com.CCC.CoCoChat.Data.Retrofit

import android.content.SharedPreferences
import com.CCC.CoCoChat.Data.Retrofit.RetrofitConst.URL
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {
    private var pref: SharedPreferences? = null
    fun init(pref: SharedPreferences) { this.pref = pref }


    private val retrofit =
        retrofit2.Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(
                OkHttpClient()
                    .newBuilder()
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .build()
            ).build()

    fun getRetrofit(): Api = retrofit.create(Api::class.java)

    interface Api {
        suspend fun login() {

        }
    }
}