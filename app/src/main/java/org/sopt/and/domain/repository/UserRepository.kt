package org.sopt.and.domain.repository

import org.sopt.and.domain.model.request.UserLoginModel
import org.sopt.and.domain.model.request.UserSignUpModel
import org.sopt.and.domain.model.response.Hobby
import org.sopt.and.domain.model.response.Token
import org.sopt.and.domain.model.response.UserNumber

interface UserRepository {
    suspend fun postUserSignUp(userSignUpModel: UserSignUpModel): Result<UserNumber>
    suspend fun postUserLogin(userLoginModel: UserLoginModel): Result<Token>
    suspend fun getUserHobby(token: String): Result<Hobby>
}