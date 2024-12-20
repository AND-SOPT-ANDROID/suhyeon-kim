package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.mapper.todata.toData
import org.sopt.and.data.mapper.todomain.toDomain
import org.sopt.and.data.remote.datasource.UserDataRemoteSource
import org.sopt.and.data.remote.utils.handleApiResponse
import org.sopt.and.domain.model.request.UserLoginModel
import org.sopt.and.domain.model.request.UserSignUpModel
import org.sopt.and.domain.model.response.Hobby
import org.sopt.and.domain.model.response.Token
import org.sopt.and.domain.model.response.UserNumber
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataRemoteSource: UserDataRemoteSource
) : UserRepository {

    override suspend fun postUserSignUp(userSignUpModel: UserSignUpModel): Result<UserNumber> {
        return runCatching {
            userDataRemoteSource.postUserSignUp(userSignUpModel.toData()).handleApiResponse().getOrThrow().toDomain()
        }
    }

    override suspend fun postUserLogin(userLoginModel: UserLoginModel): Result<Token> {
        return runCatching {
            userDataRemoteSource.postUserLogin(userLoginModel.toData()).handleApiResponse().getOrThrow().toDomain()
        }
    }

    override suspend fun getUserHobby(token: String): Result<Hobby> {
        return runCatching {
            userDataRemoteSource.getUserHobby(token = token).handleApiResponse().getOrThrow().toDomain()
        }
    }
}