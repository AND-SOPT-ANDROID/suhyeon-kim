package org.sopt.and.presentation.signup

import org.sopt.and.domain.model.request.UserSignUpModel
import org.sopt.and.domain.model.response.UserNumber
import org.sopt.and.presentation.core.base.UiEvent
import org.sopt.and.presentation.core.base.UiSideEffect
import org.sopt.and.presentation.core.base.UiState

class SignUpContract {
    sealed class SignUpState {
        data object Idle : SignUpState()
        data object Loading : SignUpState()
        data class Success(val result: UserNumber) : SignUpState()
        data class Failure(val message: String) : SignUpState()
    }

    data class SignUpUiState(
        var signUpState: SignUpState.Idle,
        val userName: String = "",
        val password: String = "",
        val hobby: String = "",
        val signUpSuccess: Boolean = false,
        val signUpError: String? = null,
        val nameErrorMsg: String = "",
        val passwordErrorMsg: String = "",
        val hobbyErrorMsg: String = ""
    ) : UiState

    sealed interface SignUpSideEffect : UiSideEffect {
    }

    sealed class SignUpEvent : UiEvent {
        data class NameChanged(val name: String) : SignUpEvent()
        data class PasswordChanged(val password: String) : SignUpEvent()
        data class HobbyChanged(val hobby: String) : SignUpEvent()
        data class SignUpClicked(val body: UserSignUpModel) : SignUpEvent()
        data class StateChanged(val signUpState: SignUpState) : SignUpEvent()
    }
}