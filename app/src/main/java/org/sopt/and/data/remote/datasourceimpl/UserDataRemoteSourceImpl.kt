package org.sopt.and.data.remote.datasourceimpl

import org.sopt.and.data.remote.datasource.UserDataRemoteSource
import org.sopt.and.data.remote.model.base.ApiResponse
import org.sopt.and.data.remote.model.request.UserLoginRequestDto
import org.sopt.and.data.remote.model.request.UserSignUpRequestDto
import org.sopt.and.data.remote.model.response.UserHobbyResponseDto
import org.sopt.and.data.remote.model.response.UserSignUpResponseDto
import org.sopt.and.data.remote.model.response.UserTokenResponseDto
import org.sopt.and.data.remote.service.UserService
import javax.inject.Inject

class UserDataRemoteSourceImpl @Inject constructor(
    private val userService: UserService
) : UserDataRemoteSource {

    override suspend fun postUserSignUp(userSignUpRequestDto: UserSignUpRequestDto): ApiResponse<UserSignUpResponseDto> =
        userService.postUserSignUp(body = userSignUpRequestDto)

    override suspend fun postUserLogin(userLoginRequestDto: UserLoginRequestDto): ApiResponse<UserTokenResponseDto> =
        userService.postUserLogin(body = userLoginRequestDto)


    override suspend fun getUserHobby(token: String): ApiResponse<UserHobbyResponseDto> =
        userService.getUserHobby(token = token)
}