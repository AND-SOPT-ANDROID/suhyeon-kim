package org.sopt.and.feature.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.and.data.ServicePool
import org.sopt.and.data.model.dto.ResponseUserTokenDto
import org.sopt.and.data.model.request.UserLoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }

    private val _userState = mutableStateOf<ResponseUserTokenDto?>(null)
    val userState: State<ResponseUserTokenDto?> get() = _userState

    fun postUserLogin(body: UserLoginRequest, callback: (ResponseUserTokenDto?) -> Unit) {
        userService.postUserLogin(
            body = body
        ).enqueue(object : Callback<ResponseUserTokenDto> {
            override fun onResponse(
                call: Call<ResponseUserTokenDto>,
                response: Response<ResponseUserTokenDto>
            ) {
                if (response.isSuccessful) {
                    _userState.value = response.body()
                    callback(response.body())

                } else {
                    val error = response.message()
                    Log.e("error", error.toString())
                    callback(null)
                }
            }

            override fun onFailure(call: Call<ResponseUserTokenDto>, t: Throwable) {
                Log.e("failure", t.message.toString())
                callback(null)
            }
        })
    }

    private val _userName = MutableLiveData("")
    val userName: LiveData<String> get() = _userName

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password


    fun setUserName(newName: String) {
        _userName.value = newName
    }

    fun setPassword(newPassword: String) {
        _password.value = newPassword
    }

    fun onLoginClick(
        localName: String,
        localPassword: String,
        onSuccess: (String, String) -> Unit,
        onFailure: () -> Unit
    ) {
        viewModelScope.launch {
            if (userName.value == localName && password.value == localPassword) {
                //로그인 성공
                onSuccess(userName.value!!, password.value!!)
            } else {
                onFailure()
            }
        }
    }
}