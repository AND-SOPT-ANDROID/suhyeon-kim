package org.sopt.and.presentation.mypage

import org.sopt.and.domain.model.response.Hobby

sealed class MyState {
    data object Idle : MyState()
    data object Loading : MyState()
    data class Success(val result: Hobby) : MyState()
    data class Failure(val message: String) : MyState()
}