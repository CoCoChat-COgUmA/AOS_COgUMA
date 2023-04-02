package com.CCC.CoCoChat.Data.Retrofit

import android.content.SharedPreferences
import com.CCC.CoCoChat.Data.Request.LoginRequest
import com.CCC.CoCoChat.Data.Response.LoginResponse
import com.CCC.CoCoChat.Data.Retrofit.RetrofitConst.LOGIN_PATH
import com.CCC.CoCoChat.Data.Retrofit.RetrofitConst.SERVER_URL
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

object Retrofit {
    private var pref: SharedPreferences? = null
    fun init(pref: SharedPreferences) { this.pref = pref }


    private val retrofit =
        retrofit2.Retrofit.Builder()
            .baseUrl(SERVER_URL)
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
        @POST(LOGIN_PATH)
        suspend fun login(
            @Body loginRequest: LoginRequest
        ): LoginResponse
    }
}