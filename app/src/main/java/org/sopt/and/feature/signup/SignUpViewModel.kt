package org.sopt.and.feature.signup

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.data.ServicePool
import org.sopt.and.data.model.dto.ResponseUserSignUpDto
import org.sopt.and.data.model.request.UserSignUpRequest
import org.sopt.and.utils.AuthKey.PASSWORD_PATTERN
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }

    private val _userState = mutableStateOf<ResponseUserSignUpDto?>(null)
    val userState: State<ResponseUserSignUpDto?> get() = _userState

    fun postUserSignUp(body: UserSignUpRequest) {
        userService.postUserSignUp(
            body = body
        ).enqueue(object : Callback<ResponseUserSignUpDto> {
            override fun onResponse(
                call: Call<ResponseUserSignUpDto>,
                response: Response<ResponseUserSignUpDto>
            ) {
                if (response.isSuccessful) {
                    _userState.value = response.body()
                    Log.d("postUserSignUp", response.body().toString())

                } else {
                    val error = response.message()
                    Log.e("error", error.toString())
                }
            }

            override fun onFailure(call: Call<ResponseUserSignUpDto>, t: Throwable) {
                Log.e("failure", t.message.toString())
            }
        })
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