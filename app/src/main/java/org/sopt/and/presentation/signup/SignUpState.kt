package org.sopt.and.presentation.signup

import org.sopt.and.domain.model.response.UserNumber

sealed class SignUpState {
    data object Idle : SignUpState()
    data object Loading : SignUpState()
    data class Success(val result: UserNumber) : SignUpState()
    data class Failure(val message: String) : SignUpState()
}
