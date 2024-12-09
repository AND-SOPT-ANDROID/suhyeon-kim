package org.sopt.and.data.remote.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSignUpResponseDto(
    @SerialName("no")
    val userId: Int
)