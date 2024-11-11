package org.sopt.and.feature.mypage

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.and.data.ServicePool
import org.sopt.and.data.model.dto.ResponseUserHobbyDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }

    private val _userState = mutableStateOf<ResponseUserHobbyDto?>(null)
    val userState: State<ResponseUserHobbyDto?> get() = _userState

    fun getUserHobby(token: String) {
        userService.getUserHobby(token = token)
            .enqueue(object : Callback<ResponseUserHobbyDto> {
                override fun onResponse(
                    call: Call<ResponseUserHobbyDto>,
                    response: Response<ResponseUserHobbyDto>
                ) {
                    if (response.isSuccessful) {
                        _userState.value = response.body()
                        Log.d("getUserHobby", response.body().toString())

                    } else {
                        val error = response.message()
                        Log.e("error", error.toString())
                    }
                }

                override fun onFailure(call: Call<ResponseUserHobbyDto>, t: Throwable) {
                    Log.e("failure", t.message.toString())
                }
            })
    }

    private var _userEmail = MutableLiveData("")
    val userEmail: LiveData<String> get() = _userEmail

    fun setUserEmail(userEmail: String) {
        _userEmail.value = userEmail
    }
}