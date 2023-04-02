package com.CCC.CoCoChat.View

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.CCC.CoCoChat.Data.RepositoryImpl.UserRepositoryImpl
import com.CCC.CoCoChat.Data.Request.LoginRequest
import com.CCC.CoCoChat.EncryptionAES.encrypt
import com.CCC.CoCoChat.EncryptionAES.generateSecretKey
import com.CCC.CoCoChat.Factory.LoginViewModelFactory
import com.CCC.CoCoChat.UiState.LoginUiState
import com.CCC.CoCoChat.ViewModel.LoginViewModel
import com.CCC.CoCoChat.databinding.ActivityLoginBinding
import java.util.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<LoginViewModel> {
        LoginViewModelFactory(UserRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            btLogin.setOnClickListener {
                viewModel.login(
                    LoginRequest(
                        etLoginId.text.toString(),
                        etLoginPw.text.toString()
                    )
                )
            }
        }

        viewModel.loginUiState.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is LoginUiState.LoginSuccess -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                is LoginUiState.LoginFailed -> {}
                else -> {}
            }
        })
    }
}
