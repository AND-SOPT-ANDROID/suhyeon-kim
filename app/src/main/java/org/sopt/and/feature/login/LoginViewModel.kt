package org.sopt.and.feature.login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.data.ServicePool
import org.sopt.and.data.model.dto.ResponseUserTokenDto
import org.sopt.and.data.model.request.UserLoginRequest
import org.sopt.and.utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }

    private val _userState = mutableStateOf<ResponseUserTokenDto?>(null)
    val userState: State<ResponseUserTokenDto?> get() = _userState

    fun postUserLogin(
        context: Context,
        body: UserLoginRequest,
        callback: (ResponseUserTokenDto?) -> Unit
    ) {
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
                    context.toast(context.getString(R.string.fail_to_login))
                }
            }

            override fun onFailure(call: Call<ResponseUserTokenDto>, t: Throwable) {
                Log.e("failure", t.message.toString())
                context.toast(context.getString(R.string.fail_to_login))
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
        onSuccess: (String, String) -> Unit,
    ) {
        viewModelScope.launch {
            onSuccess(userName.value!!, password.value!!)
        }
    }
}