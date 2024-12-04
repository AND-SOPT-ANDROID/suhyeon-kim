package org.sopt.and.presentation.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _myState = MutableStateFlow<MyState>(MyState.Idle)
    val myState: StateFlow<MyState> get() = _myState

    private val _hobby = MutableLiveData("")
    val hobby: LiveData<String> get() = _hobby

    fun getUserHobby(token: String) {
        _myState.value = MyState.Loading
        viewModelScope.launch {
            val result = userRepository.getUserHobby(token = token)
            _myState.value = result.fold(
                onSuccess = { response ->
                    Log.d("HobbySuccess", response.hobby)
                    _hobby.value = response.hobby
                    MyState.Success(response)
                },
                onFailure = { error ->
                    Log.e("getUserHobbyError", error.toString())
                    MyState.Failure(error.message.toString())
                }
            )
        }
    }
}