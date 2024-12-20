package org.sopt.and.presentation.mypage

import org.sopt.and.domain.model.response.Hobby
import org.sopt.and.presentation.core.base.UiEvent
import org.sopt.and.presentation.core.base.UiSideEffect
import org.sopt.and.presentation.core.base.UiState
import org.sopt.and.utils.AuthKey.DEFAULT_NAME

sealed class MyState {
    data object Idle : MyState()
    data object Loading : MyState()
    data class Success(val result: Hobby) : MyState()
    data class Failure(val message: String) : MyState()
}

class MyContract {
    data class MyUiState(
        val myState: MyState = MyState.Idle,
        val hobby: String = DEFAULT_NAME,
    ) : UiState

    sealed interface MySideEffect : UiSideEffect

    sealed class MyEvent : UiEvent {
        data class GetUserHobby(val token: String) : MyEvent()
        data class StateChanged(val myState: MyState) : MyEvent()
    }
}