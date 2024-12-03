package org.sopt.and.feature.mypage

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.and.data.ServicePool
import org.sopt.and.data.remote.model.response.UserHobbyResponseDto

class MyViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }

    private val _userState = mutableStateOf<UserHobbyResponseDto?>(null)
    val userState: State<UserHobbyResponseDto?> get() = _userState

    private val _hobby = MutableLiveData("")
    val hobby: LiveData<String> get() = _hobby

    fun getUserHobby(token: String) {
        viewModelScope.launch {
            runCatching {
                userService.getUserHobby(token = token)
            }.onSuccess { response ->
                _userState.value = response.result
                _hobby.value = response.result.hobby
            }.onFailure { error ->
                Log.e("getUserHobbyError", error.toString())
            }
        }
    }
}