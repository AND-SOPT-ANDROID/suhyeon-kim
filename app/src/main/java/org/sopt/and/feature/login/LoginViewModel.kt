package org.sopt.and.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData("")
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password


    fun setEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun setPassword(newPassword: String) {
        _password.value = newPassword
    }

    fun onLoginClick(
        localEmail: String,
        localPassword: String,
        onSuccess: (String, String) -> Unit,
        onFailure: () -> Unit
    ) {
        viewModelScope.launch {
            if (email.value == localEmail && password.value == localPassword) {
                //로그인 성공
                onSuccess(email.value!!, password.value!!)
            } else {
                onFailure()
            }
        }
    }
}