package com.CCC.CoCoChat.Data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import com.CCC.CoCoChat.Data.Repository.User
import com.google.gson.Gson
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class SharedPreferences(context: Context) {
    private val keyStore = KeyStore.getInstance("AndroidKeyStore")
    val pref = context.getSharedPreferences(context.packageName, MODE_PRIVATE)

    init {
        keyStore.load(null)
    }

    var user: User?
        get() {
            return Gson().fromJson(decoding("user","User_Generate_Key"), User::class.java)
        }
        set(value) {
            pref.edit().putString("user", encoding("User_Generate_Key", Gson().toJson(value))).apply()
        }

    private fun encoding(key: String, value: String): String {
        val keyG = generateKey(key)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, keyG)
        val encryptedUserId = cipher.doFinal(value.toByteArray())
        return Base64.encodeToString(encryptedUserId, Base64.DEFAULT)
    }

    private fun decoding(key: String, keyG: String): String? {
        val encryptedUserId = pref.getString(key, null) ?: return null
        val keyG = generateKey(keyG)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, keyG)
        return (cipher.doFinal(Base64.decode(encryptedUserId, Base64.DEFAULT))).toString()
    }

    private fun generateKey(key: String): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
        val keyGenParameterSpec =
            KeyGenParameterSpec
                .Builder(
                    key,
                    KeyProperties.PURPOSE_ENCRYPT or
                            KeyProperties.PURPOSE_DECRYPT
                )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(256)
                .build()
        keyGenerator.init(keyGenParameterSpec)
        return keyGenerator.generateKey()
    }

}