package org.sopt.and.data.mapper.todomain

import org.sopt.and.data.remote.model.response.UserHobbyResponseDto
import org.sopt.and.domain.model.response.Hobby

fun UserHobbyResponseDto.toDomain(): Hobby = Hobby(
    hobby = this.hobby
)