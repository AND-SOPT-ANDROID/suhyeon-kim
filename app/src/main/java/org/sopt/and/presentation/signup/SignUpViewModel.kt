package org.sopt.and.presentation.signup

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.domain.model.request.UserSignUpModel
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.utils.AuthKey.PASSWORD_PATTERN
import org.sopt.and.utils.handleErrorToast
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> get() = _signUpState

    fun postUserSignUp(context: Context, body: UserSignUpModel) {
        _signUpState.value = SignUpState.Loading
        viewModelScope.launch {
            val result = userRepository.postUserSignUp(
                userSignUpModel = body
            )
            _signUpState.value = result.fold(
                onSuccess = { response ->
                    SignUpState.Success(response)
                },
                onFailure = { error ->
                    handleErrorToast(
                        exception = error,
                        is400Error = R.string.fail_to_signup_maximum_length,
                        is409Error = R.string.fail_to_signup_duplicate_name,
                        context = context
                    )
                    SignUpState.Failure(error.message.orEmpty())
                }
            )
        }
    }

    private val _userName = MutableLiveData("")
    val userName: LiveData<String> get() = _userName

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    private val _hobby = MutableLiveData("")
    val hobby: LiveData<String> get() = _hobby

    var nameErrorMsg by mutableStateOf("")

    var passwordErrorMsg by mutableStateOf("")

    var hobbyErrorMsg by mutableStateOf("")

    private val _showDialog = MutableLiveData(false)
    val showDialog: LiveData<Boolean> get() = _showDialog

    private val regex = Regex(PASSWORD_PATTERN)

    fun setUserName(newName: String) {
        _userName.value = newName
    }

    fun setPassword(newPassword: String) {
        _password.value = newPassword
    }

    fun setHobby(newHobby: String) {
        _hobby.value = newHobby
    }

    fun setDialogState(dialogState: Boolean) {
        _showDialog.value = dialogState
    }

    fun onSignUpClick(
        localName: String,
        localPassword: String,
        localHobby: String,
        onSuccess: (String, String, String) -> Unit,
        onFailure: () -> Unit,
        context: Context
    ) {
        viewModelScope.launch {
            validateInputs(
                name = localName,
                password = localPassword,
                hobby = localHobby,
                context = context
            ) //검증
            if (nameErrorMsg.isEmpty() && passwordErrorMsg.isEmpty() && hobbyErrorMsg.isEmpty()) {
                setDialogState(false)

                //회원가입 정보 저장
                setUserName(localName)
                setPassword(localPassword)
                setHobby(localHobby)
                onSuccess(userName.value!!, password.value!!, hobby.value!!)
            } else {
                onFailure()
            }
        }
    }

    // 이메일 및 비밀번호 검증 함수
    fun validateInputs(name: String, password: String, hobby: String, context: Context) {
        nameErrorMsg = if (name.length > 8) {
            context.getString(R.string.invalid_user_name)
        } else {
            ""
        }

        passwordErrorMsg = if (password.length > 8) {
            context.getString(R.string.invalid_password)
        } else {
            ""
        }

        hobbyErrorMsg = if (hobby.length > 8) {
            context.getString(R.string.invalid_hobby)
        } else {
            ""
        }
    }

    //비밀번호 검증 함수
    private fun isValidPassword(password: String): Boolean {
        return password.matches(regex)
    }
}