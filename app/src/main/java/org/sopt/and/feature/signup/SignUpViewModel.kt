package org.sopt.and.feature.signup

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.utils.AuthKey.PASSWORD_PATTERN

class SignUpViewModel : ViewModel() {
    private val _email = MutableLiveData("")
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    val showPassword = mutableStateOf(false)

    var emailErrorMsg by mutableStateOf("")

    var passwordErrorMsg by mutableStateOf("")

    private val _showDialog = MutableLiveData(false)
    val showDialog: LiveData<Boolean> get() = _showDialog

    private val regex = Regex(PASSWORD_PATTERN)

    fun setEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun setPassword(newPassword: String) {
        _password.value = newPassword
    }

    fun setDialogState(dialogState: Boolean) {
        _showDialog.value = dialogState
    }

    fun onSignUpClick(
        localEmail: String,
        localPassword: String,
        onSuccess: (String, String) -> Unit,
        onFailure: () -> Unit,
        context: Context
    ) {
        viewModelScope.launch {
            validateInputs(email = localEmail, password = localPassword, context = context) //검증
            if (emailErrorMsg.isEmpty() && passwordErrorMsg.isEmpty()) {
                setDialogState(false)

                //회원가입 정보 저장
                setEmail(localEmail)
                setPassword(localPassword)
                onSuccess(email.value!!, password.value!!)
            } else {
                onFailure()
            }
        }
    }

    // 이메일 및 비밀번호 검증 함수
    fun validateInputs(email: String, password: String, context: Context) {
        emailErrorMsg = if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            context.getString(R.string.invalid_email_address)
        } else {
            ""
        }

        passwordErrorMsg = if (!isValidPassword(password)) {
            context.getString(R.string.invalid_password)
        } else {
            ""
        }
    }

    //비밀번호 검증 함수
    private fun isValidPassword(password: String): Boolean {
        return password.matches(regex)
    }
}