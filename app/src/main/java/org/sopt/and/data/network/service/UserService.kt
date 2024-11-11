package org.sopt.and.data.network.service

import org.sopt.and.data.model.dto.ResponseUserHobbyDto
import org.sopt.and.data.model.dto.ResponseUserSignUpDto
import org.sopt.and.data.model.dto.ResponseUserTokenDto
import org.sopt.and.data.model.request.UserLoginRequest
import org.sopt.and.data.model.request.UserSignUpRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    fun postUserSignUp(
        @Body body: UserSignUpRequest
    ): Call<ResponseUserSignUpDto>

    @POST("/login")
    fun postUserLogin(
        @Body body: UserLoginRequest
    ): Call<ResponseUserTokenDto>

    @GET("/user/my-hobby")
    fun getUserHobby(
        @Header("token") token: String,
    ): Call<ResponseUserHobbyDto>
}