package org.sopt.and.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.sopt.and.utils.AuthKey.PASSWORD_PATTERN

class SignUpViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    val showPassword = mutableStateOf(false)

    var emailErrorMsg by mutableStateOf("")
    var passwordErrorMsg by mutableStateOf("")

    val showDialog = mutableStateOf(false)
    private val regex = Regex(PASSWORD_PATTERN)

    // 이메일 및 비밀번호 검증 함수
    fun validateInputs() {
        emailErrorMsg = if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            "유효한 이메일 형식이 아닙니다."
        } else {
            ""
        }

        passwordErrorMsg = if (!isValidPassword(password)) {
            "비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용해야 합니다."
        } else {
            ""
        }
    }

    //비밀번호 검증 함수
    private fun isValidPassword(password: String): Boolean {
        return password.matches(regex)
    }
}