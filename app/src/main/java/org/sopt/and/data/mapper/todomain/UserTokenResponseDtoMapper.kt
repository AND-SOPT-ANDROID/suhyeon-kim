package org.sopt.and.data.mapper.todomain

import org.sopt.and.data.remote.model.response.UserTokenResponseDto
import org.sopt.and.domain.model.response.Token

fun UserTokenResponseDto.toDomain(): Token = Token(
    token = this.token
)