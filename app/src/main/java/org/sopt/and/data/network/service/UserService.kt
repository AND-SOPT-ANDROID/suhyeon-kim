package org.sopt.and.data.network.service

import org.sopt.and.data.remote.model.response.UserHobbyResponseDto
import org.sopt.and.data.remote.model.response.UserSignUpResponseDto
import org.sopt.and.data.remote.model.response.UserTokenResponseDto
import org.sopt.and.data.remote.model.request.UserLoginRequestDto
import org.sopt.and.data.remote.model.request.UserSignUpRequestDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    fun postUserSignUp(
        @Body body: UserSignUpRequestDto
    ): Call<UserSignUpResponseDto>

    @POST("/login")
    fun postUserLogin(
        @Body body: UserLoginRequestDto
    ): Call<UserTokenResponseDto>

    @GET("/user/my-hobby")
    fun getUserHobby(
        @Header("token") token: String,
    ): Call<UserHobbyResponseDto>
}