package com.CCC.CoCoChat.UiState

sealed class LoginUiState {
    data class LoginFailed(
        val message: String?
    ): LoginUiState()

    data class LoginError(
        val message: String?
    ): LoginUiState()
    object LoginSuccess: LoginUiState()
}