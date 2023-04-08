package com.CCC.CoCoChat.View

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.CCC.CoCoChat.Data.Request.LoginRequest
import com.CCC.CoCoChat.UiState.LoginUiState
import com.CCC.CoCoChat.ViewModel.LoginViewModel
import com.CCC.CoCoChat.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            btLogin.setOnClickListener {
                loginViewModel.login(
                    LoginRequest(
                        etLoginId.text.toString(),
                        etLoginPw.text.toString()
                    )
                )
            }
        }

        loginViewModel.loginUiState.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is LoginUiState.LoginSuccess -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                is LoginUiState.LoginFailed -> {}
                else -> {

                }
            }
        })
    }
}
