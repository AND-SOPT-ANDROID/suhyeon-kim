package org.sopt.and.feature.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okio.IOException
import org.sopt.and.R
import org.sopt.and.data.ServicePool
import org.sopt.and.data.remote.model.request.UserLoginRequestDto
import org.sopt.and.utils.toast
import retrofit2.HttpException

class LoginViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }

    private val _token = MutableLiveData<String>(null)
    val token: LiveData<String> get() = _token

    fun postUserLogin(
        context: Context,
        body: UserLoginRequestDto,
    ) {
        viewModelScope.launch {
            runCatching {
                userService.postUserLogin(body = body)
            }.onSuccess { response ->
                _token.value = response.result.token
            }.onFailure { error ->
                when (error) {
                    is HttpException -> {
                        when (error.code()) {
                            400 -> context.toast(context.getString(R.string.fail_to_login))
                            403 -> context.toast(context.getString(R.string.fail_to_login_invalid_password))
                        }
                    }

                    is IOException -> {
                        context.toast(context.getString(R.string.fail_to_network))
                    }

                    else -> {
                        context.toast(context.getString(R.string.fail_to_login))
                    }
                }
                Log.e("postUserLoginError", error.toString())
            }
        }
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
}