package org.sopt.and.domain.model.request

data class UserSignUpModel(
    val username: String,
    val password: String,
    val hobby: String
)
