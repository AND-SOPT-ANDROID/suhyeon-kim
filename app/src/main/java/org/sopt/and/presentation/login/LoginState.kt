package org.sopt.and.presentation.login

import org.sopt.and.domain.model.response.Token

sealed class LoginState {
    data object Idle: LoginState()
    data object Loading: LoginState()
    data class Success(val result: Token): LoginState()
    data class Failure(val message: String): LoginState()
}