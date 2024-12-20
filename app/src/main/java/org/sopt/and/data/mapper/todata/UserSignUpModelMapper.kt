package org.sopt.and.data.mapper.todata

import org.sopt.and.data.remote.model.request.UserSignUpRequestDto
import org.sopt.and.domain.model.request.UserSignUpModel

fun UserSignUpModel.toData() : UserSignUpRequestDto = UserSignUpRequestDto(
    username = this.username,
    password = this.password,
    hobby = this.hobby
)