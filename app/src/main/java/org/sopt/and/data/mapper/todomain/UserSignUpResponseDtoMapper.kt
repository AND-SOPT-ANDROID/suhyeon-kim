package org.sopt.and.data.mapper.todomain

import org.sopt.and.data.remote.model.response.UserSignUpResponseDto
import org.sopt.and.domain.model.response.UserNumber

fun UserSignUpResponseDto.toDomain(): UserNumber = UserNumber(
    userId = this.userId
)