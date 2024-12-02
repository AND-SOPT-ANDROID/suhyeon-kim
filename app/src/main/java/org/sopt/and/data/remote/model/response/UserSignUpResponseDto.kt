package org.sopt.and.data.remote.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSignUpResponseDto(
    @SerialName("result")
    val result: Result
) {
    @Serializable
    data class Result(
        @SerialName("no")
        val no: Int
    )
}