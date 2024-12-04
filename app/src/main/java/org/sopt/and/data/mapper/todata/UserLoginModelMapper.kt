package org.sopt.and.data.mapper.todata

import org.sopt.and.data.remote.model.request.UserLoginRequestDto
import org.sopt.and.domain.model.request.UserLoginModel

fun UserLoginModel.toData() : UserLoginRequestDto = UserLoginRequestDto(
    username = this.username,
    password = this.password
)