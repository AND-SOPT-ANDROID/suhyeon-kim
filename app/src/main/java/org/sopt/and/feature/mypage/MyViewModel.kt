package org.sopt.and.feature.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private var _userEmail = MutableLiveData<String>()
    fun setUserEmail(userEmail: String) {
        _userEmail.value = userEmail
    }

    val userEmail: LiveData<String>
        get() = _userEmail
}