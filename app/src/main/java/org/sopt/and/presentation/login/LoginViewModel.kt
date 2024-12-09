package org.sopt.and.presentation.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.domain.model.request.UserLoginModel
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.utils.handleErrorToast
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
                    _token.value = response.token
                    onSuccess()
                    LoginState.Success(response)
                },
                onFailure = { error ->
                    handleErrorToast(
                        exception = error,
                        is400Error = R.string.fail_to_login,
                        is403Error = R.string.fail_to_login_invalid_password,
                        context = context
                    )
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