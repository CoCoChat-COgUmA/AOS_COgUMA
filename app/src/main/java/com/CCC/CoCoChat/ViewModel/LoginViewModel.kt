package com.CCC.CoCoChat.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CCC.CoCoChat.Data.Repository.LoginRepository
import com.CCC.CoCoChat.Data.Request.LoginRequest
import com.CCC.CoCoChat.UiState.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {
    private val _loginUiState = MutableLiveData<LoginUiState>()
    val loginUiState: LiveData<LoginUiState> = _loginUiState

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            loginRepository.login(loginRequest).fold(
                onSuccess = {
                    when {
                        it.result == "success" ->
                            _loginUiState.value = LoginUiState.LoginSuccess
                        it.code == "1102" ->
                            _loginUiState.value = LoginUiState.LoginFailed(it.message)
                    }
                },
                onFailure = {
                    _loginUiState.value = LoginUiState.LoginError(it.message)
                }
            )
        }
    }
}