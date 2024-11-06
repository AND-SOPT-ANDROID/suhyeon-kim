package org.sopt.and.feature.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData("")
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    val showPassword = mutableStateOf(false)

    var emailErrorMsg by mutableStateOf("")

    var passwordErrorMsg by mutableStateOf("")

    val showDialog = mutableStateOf(false)

    fun changeEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun changePassword(newPassword: String) {
        _password.value = newPassword
    }
}