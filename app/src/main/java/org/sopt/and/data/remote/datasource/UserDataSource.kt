package org.sopt.and.data.remote.datasource

interface UserDataSource {
    suspend fun postUserSignUp() : ApiResponse<UserSignUpResponseDto>
    suspend fun postUserLogin() : ApiResponse<UserTokenResponseDto>
    suspend fun getUserHobby() : ApiResponse<UserHobbyResponseDto>
}