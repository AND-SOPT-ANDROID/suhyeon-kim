package org.sopt.and.data.remote.datasource

import org.sopt.and.data.remote.model.base.ApiResponse
import org.sopt.and.data.remote.model.request.UserLoginRequestDto
import org.sopt.and.data.remote.model.request.UserSignUpRequestDto
import org.sopt.and.data.remote.model.response.UserHobbyResponseDto
import org.sopt.and.data.remote.model.response.UserSignUpResponseDto
import org.sopt.and.data.remote.model.response.UserTokenResponseDto

interface UserDataRemoteSource {
    suspend fun postUserSignUp(userSignUpRequestDto: UserSignUpRequestDto): ApiResponse<UserSignUpResponseDto>
    suspend fun postUserLogin(userLoginRequestDto: UserLoginRequestDto): ApiResponse<UserTokenResponseDto>
    suspend fun getUserHobby(token: String): ApiResponse<UserHobbyResponseDto>
}