package org.sopt.and.data.network.service

import org.sopt.and.data.remote.model.base.ApiResponse
import org.sopt.and.data.remote.model.request.UserLoginRequestDto
import org.sopt.and.data.remote.model.request.UserSignUpRequestDto
import org.sopt.and.data.remote.model.response.UserHobbyResponseDto
import org.sopt.and.data.remote.model.response.UserSignUpResponseDto
import org.sopt.and.data.remote.model.response.UserTokenResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    suspend fun postUserSignUp(
        @Body body: UserSignUpRequestDto
    ): ApiResponse<UserSignUpResponseDto>

    @POST("/login")
    suspend fun postUserLogin(
        @Body body: UserLoginRequestDto
    ): ApiResponse<UserTokenResponseDto>

    @GET("/user/my-hobby")
    suspend fun getUserHobby(
        @Header("token") token: String,
    ): ApiResponse<UserHobbyResponseDto>
}