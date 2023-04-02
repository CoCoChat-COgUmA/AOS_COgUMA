package com.CCC.CoCoChat.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CCC.CoCoChat.Data.Repository.UserRepository
import com.CCC.CoCoChat.Data.Request.LoginRequest
import com.CCC.CoCoChat.UiState.LoginUiState
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _loginUiState = MutableLiveData<LoginUiState>()
    val loginUiState: LiveData<LoginUiState> = _loginUiState

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            userRepository.signIn(loginRequest).fold(
                onSuccess = {
                        _loginUiState.value = LoginUiState.LoginSuccess
                    },
                onFailure = {
                    if (it is HttpException) {
                        when (it.code()) {
                            1102 -> _loginUiState.value =
                                LoginUiState.LoginFailed(it.message())
                            else -> _loginUiState.value =
                                LoginUiState.LoginFailed(it.message())
                        }
                    }
                }
            )
        }
    }
}