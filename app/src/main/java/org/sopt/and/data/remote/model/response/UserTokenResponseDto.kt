package org.sopt.and.data.remote.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserTokenResponseDto(
    @SerialName("token")
    val token: String
)