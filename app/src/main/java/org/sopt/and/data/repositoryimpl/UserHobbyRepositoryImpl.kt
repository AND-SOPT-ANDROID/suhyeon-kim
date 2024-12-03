package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.remote.datasource.UserDataSource
import org.sopt.and.domain.repository.UserHobbyRepository

class UserHobbyRepositoryImpl @Inject constructor(
    private val userHobbyDataSource: UserDataSource
) : UserHobbyRepository {

}