package org.sopt.and.presentation.mypage

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.core.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<MyContract.MyUiState, MyContract.MySideEffect, MyContract.MyEvent>() {
    override fun createInitialState(): MyContract.MyUiState = MyContract.MyUiState()

    override suspend fun handleEvent(event: MyContract.MyEvent) {
        when (event) {
            is MyContract.MyEvent.GetUserHobby -> getUserHobby(token = event.token)
            is MyContract.MyEvent.StateChanged -> setState { copy(myState = event.myState) }
        }
    }

    fun getUserHobby(token: String) {
        setState { copy(myState = MyState.Loading) }
        viewModelScope.launch {
            val result = userRepository.getUserHobby(token = token)
            result.fold(
                onSuccess = { response ->
                    setState { copy(hobby = response.hobby) }
                    setState { copy(myState = MyState.Success(response)) }
                },
                onFailure = { error ->
                    setState { copy(myState = MyState.Failure(error.message.toString())) }
                }
            )
        }
    }
}