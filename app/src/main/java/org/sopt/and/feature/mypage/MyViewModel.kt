package org.sopt.and.feature.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private var _userEmail = MutableLiveData("")
    val userEmail: LiveData<String> get() = _userEmail

    fun setUserEmail(userEmail: String) {
        _userEmail.value = userEmail
    }
}