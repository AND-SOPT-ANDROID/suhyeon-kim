package org.sopt.and.presentation.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException
import org.sopt.and.R
import org.sopt.and.domain.model.request.UserLoginModel
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.utils.toast
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val _token = MutableLiveData<String>(null)
    val token: LiveData<String> get() = _token

    fun postUserLogin(
        context: Context,
        body: UserLoginModel,
        onSuccess: () -> Unit
    ) {
        _loginState.value = LoginState.Loading
        viewModelScope.launch {
            val result = userRepository.postUserLogin(
                userLoginModel = body
            )
            _loginState.value = result.fold(
                onSuccess = { response ->
                    Log.d("LoginSuccess", response.token)
                    _token.value = response.token
                    onSuccess()
                    LoginState.Success(response)
                },
                onFailure = { error ->
                    when (error) {
                        is HttpException -> {
                            when (error.code()) {
                                400 -> context.toast(context.getString(R.string.fail_to_login))
                                403 -> context.toast(context.getString(R.string.fail_to_login_invalid_password))
                            }
                        }

                        is IOException -> {
                            Log.e("NetworkError", "IOException occurred: ${error.message}")
                            context.toast(context.getString(R.string.fail_to_network))
                        }

                        else -> {
                            context.toast(context.getString(R.string.fail_to_login))
                        }
                    }
                    LoginState.Failure(error.message.orEmpty())
                }
            )
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